package com.kandidato.manager.candidate;

import com.kandidato.model.dto.BaseCandidateDTO;
import com.kandidato.model.dto.CandidateDTO;
import com.kandidato.model.dto.TimelineFieldDTO;
import com.kandidato.model.dto.TimelineFieldTypeDTO;
import com.kandidato.model.dto.input.CandidateIn;
import com.kandidato.model.dto.input.FieldIn;
import com.kandidato.model.dto.input.TagsIn;
import com.kandidato.persistence.entity.Candidate;
import com.kandidato.persistence.entity.TimelineField;
import com.kandidato.persistence.entity.TimelineFieldType;
import com.kandidato.persistence.entity.User;
import com.kandidato.persistence.entity.tag.Tag;
import com.kandidato.persistence.repository.candidate.CandidateRepository;
import com.kandidato.persistence.repository.timeline.TimelineFieldRepository;
import com.kandidato.persistence.repository.timeline.TimelineFieldTypeRepository;
import com.kandidato.security.model.CurrentProfile;
import com.kandidato.util.DozerUtil;
import com.kandidato.util.KandidatoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CandidateManagerImpl implements CandidateManager {
    private static final Logger log = LoggerFactory.getLogger(CandidateManagerImpl.class);

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private TimelineFieldTypeRepository timelineFieldTypeRepository;
    @Autowired
    private TimelineFieldRepository timelineFieldRepository;
    @Autowired
    private DozerUtil dozerUtil;

    @Override
    public BaseCandidateDTO create(CurrentProfile currentUser, CandidateIn candidateIn) {
        Candidate candidate = new Candidate();
        List<TimelineField> fields = new ArrayList<>();

        candidate.setCreator(currentUser.getUser());
        //TODO seems we expect reuse tags but actually we create each time new one and I dont see how to fix it properly without redesigning UI
        for (String tagIn : candidateIn.getTags()) {
            Tag tag = new Tag();
            tag.setKeyword(tagIn);
            candidate.getTags().add(tag);
        }
        for (FieldIn fieldIn : candidateIn.getFields()) {
            TimelineFieldType type = getTimelineFieldType(fieldIn, currentUser.getUser());
            TimelineField field = dozerUtil.map(fieldIn, TimelineField.class);
            field.setCreator(currentUser.getUser());
            field.setType(type);
            field.setCandidate(candidate);
            fields.add(field);
        }
        candidate.setTimelineFields(fields);
        candidateRepository.save(candidate);
        BaseCandidateDTO candidateDTO = dozerUtil.map(candidate, BaseCandidateDTO.class);
        candidateDTO.setSummaryCard(CandidateDTO.buildSummaryCard(candidate.getTimelineFields()));
        return candidateDTO;
    }

    @Override
    public BaseCandidateDTO getCandidate(long id) {
        log.debug("find candidate by id: {}", id);
        Candidate candidate = this.candidateRepository.findOne(id);
        log.debug("found candidate by id: {} - result: {}", id, candidate);
        BaseCandidateDTO candidateDTO = dozerUtil.map(candidate, BaseCandidateDTO.class);
        candidateDTO.setSummaryCard(CandidateDTO.buildSummaryCard(candidate.getTimelineFields()));
        return candidateDTO;
    }

    @Override
    public CandidateDTO getCandidateProfile(long id) {
        log.debug("find candidate by id: {}", id);
        Candidate candidate = this.candidateRepository.findOne(id);
        log.debug("found candidate by id: {} - result: {}", id, candidate);
        CandidateDTO candidateDTO = dozerUtil.map(candidate, CandidateDTO.class);
        candidateDTO.setSummaryCard(CandidateDTO.buildSummaryCard(candidate.getTimelineFields()));
        candidateDTO.setTimelineRecords(CandidateDTO.buildTimeline(candidate.getTimelineFields()));
        return candidateDTO;
    }

    @Override
    public List<BaseCandidateDTO> filterByNameAndEmail(String name, String email, User user, String sortBy, Sort.Direction direction, int page, int size) {
        final PageRequest pageRequest = new PageRequest(page, size, direction, sortBy);
        List<Candidate> candidates = new ArrayList<>();

        if (email == null && name == null) {
            candidates = candidateRepository.find(user.getId(), pageRequest);
        } else if (email == null) {
            candidates = candidateRepository.findByName(name, user.getId(), pageRequest);
        } else if (name == null) {
            candidates = candidateRepository.findByMail(email, user.getId(), pageRequest);
        } else {
            candidates = candidateRepository.findByNameAndMail(name, email, user.getId(), pageRequest);
        }

        log.debug("Found candidates with  email: {} and name: {} - result: {}", email, name, candidates.size());
        return dozerUtil.map(candidates, BaseCandidateDTO.class);
    }


    @Override
    public void remove(long id) {
        this.candidateRepository.delete(id);
    }

    @Override
    public void update(CandidateDTO candidate) {
        Candidate entity = dozerUtil.map(candidate, Candidate.class);
        this.candidateRepository.save(entity);
    }

    @Override
    public List<BaseCandidateDTO> findByOwner(long ownerId) {
        log.debug("find candidates by ownerid: {}", ownerId);
        List<Candidate> candidates = this.candidateRepository.findByCreatorId(ownerId);
        log.debug("found candidates by ownerId: {} - result: {}", ownerId, candidates.size());
        List<BaseCandidateDTO> result = new ArrayList<>();
        for (Candidate candidate : candidates) {
            BaseCandidateDTO candidateDTO = dozerUtil.map(candidate, BaseCandidateDTO.class);
            candidateDTO.setSummaryCard(CandidateDTO.buildSummaryCard(candidate.getTimelineFields()));
            result.add(candidateDTO);
        }
        return result;
    }

    @Override
    public List<BaseCandidateDTO> findAvailable(long vacancyId, long creatorId, int page, int size) {
        log.debug("find candidates recommended for vacancyId: {} CreatorId: {}", vacancyId, creatorId);
        List<Candidate> candidates = candidateRepository.findNotConnectedToVacancy(vacancyId, creatorId,
                new PageRequest(page, size));
        log.debug("found candidates recommended for vacancyId: {} - result: {}", vacancyId, candidates.size());
        List<BaseCandidateDTO> result = new ArrayList<>();
        for (Candidate candidate : candidates) {
            BaseCandidateDTO candidateDTO = dozerUtil.map(candidate, BaseCandidateDTO.class);
            candidateDTO.setSummaryCard(CandidateDTO.buildSummaryCard(candidate.getTimelineFields()));
            result.add(candidateDTO);
        }
        return result;
    }

    @Override
    public BaseCandidateDTO addField(long userId, long candidateId, TimelineFieldDTO fieldDto) {

        if (fieldDto.getType().getId() == null && StringUtils.isEmpty(fieldDto.getType().getName())) {
            log.warn("We received timeline field with empty type! candidateId: {} filed: {}", candidateId, fieldDto);
            return getCandidate(candidateId);
        }
        TimelineField field = createTimelineField(new User(userId), new Candidate(candidateId), fieldDto);
        timelineFieldRepository.save(field);

        return getCandidate(candidateId);
    }


    @Override
    public BaseCandidateDTO updateField(long fieldId, TimelineFieldDTO fieldDto) {
        TimelineField field = timelineFieldRepository.getOne(fieldId);
        if (field.getCreateTime().getTime() == KandidatoUtil.getToday().getTime()) {
            updateField(fieldDto, field);
        } else {
            createField(field, fieldDto);
        }
        return getCandidate(field.getCandidate().getId());
    }

    private TimelineField createField(TimelineField field, TimelineFieldDTO fieldDto) {
        TimelineField newField = createTimelineField(field.getCreator(), field.getCandidate(), fieldDto);
        newField.setParentUuid(field.getParentUuid());
        timelineFieldRepository.save(newField);
        return newField;
    }


    private TimelineField updateField(TimelineFieldDTO fieldDto, TimelineField field) {
        field.setFieldValue(fieldDto.getFieldValue());
        field.setName(fieldDto.getFieldName());
        //TODO is it possible?
        field.getType().setPriorityGroup(fieldDto.getType().getPriorityGroup());
        timelineFieldRepository.save(field);
        return field;
    }

    @Override
    public void removeField(long fieldId) {
        timelineFieldRepository.delete(fieldId);
    }


    private TimelineField createTimelineField(User user, Candidate candidate, TimelineFieldDTO fieldDto) {
        TimelineField newField = new TimelineField();
        newField.setFieldValue(fieldDto.getFieldValue());
        newField.setName(fieldDto.getFieldName());
        newField.setCreator(user);
        newField.setCandidate(candidate);
        newField.setCreateTime(KandidatoUtil.getToday());
        TimelineFieldType type = getTimelineFieldType(fieldDto, newField.getCreator());
        newField.setType(type);
        return newField;
    }

    private TimelineFieldType getTimelineFieldType(TimelineFieldDTO field, User user) {
        TimelineFieldType type;
        if (field.getType().getId() == null) {
            type = createNewFieldType(field, user);
        } else {
            type = timelineFieldTypeRepository.findOne(field.getType().getId());
            type.setPriorityGroup(field.getType().getPriorityGroup());
        }
        return type;
    }

    private TimelineFieldType getTimelineFieldType(FieldIn field, User user) {
        TimelineFieldType type;
        if (field.getType().getId() == null) {
            type = createNewFieldType(field, user);
        } else {
            type = timelineFieldTypeRepository.findOne(field.getType().getId());
            type.setPriorityGroup(field.getType().getPriorityGroup());
        }
        return type;
    }

    private TimelineFieldType createNewFieldType(TimelineFieldDTO field, User user) {
        TimelineFieldType type = new TimelineFieldType();
        type.setName(field.getType().getName());
        type.setPriorityGroup(field.getType().getPriorityGroup());
        type.setCreator(user);
        timelineFieldTypeRepository.save(type);
        return type;
    }

    private TimelineFieldType createNewFieldType(FieldIn field, User user) {
        TimelineFieldType type = new TimelineFieldType();
        type.setName(field.getType().getName());
        type.setPriorityGroup(field.getType().getPriorityGroup());
        type.setCreator(user);
        timelineFieldTypeRepository.save(type);
        return type;
    }


    private boolean sameFieldType(TimelineFieldDTO field, TimelineField definedField) {
        return definedField.getType().getId().equals(field.getType().getId());
    }


    @Override
    public List<TimelineFieldTypeDTO> findFieldTypes(long userId) {
        return dozerUtil.map(timelineFieldTypeRepository.findByCreatorId(userId), TimelineFieldTypeDTO.class);
    }

    @Override
    public List<BaseCandidateDTO> find(Long creatorId, String sortBy, String direction, int page, int size) {
        log.debug("find candidates by ownerid: {}", creatorId);
        List<Candidate> candidates = this.candidateRepository.find(creatorId, new PageRequest(page, size)); //TODO adjust pagerequest
        log.debug("found candidates by ownerId: {} - result: {}", creatorId, candidates.size());
        List<BaseCandidateDTO> result = new ArrayList<>();
        for (Candidate candidate : candidates) {
            BaseCandidateDTO candidateDTO = dozerUtil.map(candidate, BaseCandidateDTO.class);
            candidateDTO.setSummaryCard(CandidateDTO.buildSummaryCard(candidate.getTimelineFields()));
            result.add(candidateDTO);
        }
        return result;
    }

    @Override
    public BaseCandidateDTO addTags(long candidateId, TagsIn tagsIn) {
        log.debug("add tags to candidate id: {}", candidateId);
        Candidate candidate = candidateRepository.findOne(candidateId);

        for (String tag : tagsIn.getTags()) {
            Tag newTag = new Tag();
            newTag.setKeyword(tag);

            candidate.getTags().add(newTag);
        }

        return dozerUtil.map(candidateRepository.save(candidate),
                BaseCandidateDTO.class);
    }

    @Override
    public void removeTag(long candidateId, long tagId) {
        log.debug("remove tag {} from candidate id: {}", tagId, candidateId);
        Candidate candidate = candidateRepository.findOne(candidateId);
        for (Iterator<Tag> iterator = candidate.getTags().iterator(); iterator.hasNext(); ) {
            Tag tag = iterator.next();
            if (tag.getId() == tagId) {
                iterator.remove();
                break;
            }
        }
        candidateRepository.save(candidate);
    }

}
