package com.kandidato.manager.vacancy;

import com.kandidato.constants.VacancyState;
import com.kandidato.model.dto.VacancyDTO;
import com.kandidato.model.dto.input.TagsIn;
import com.kandidato.model.dto.input.VacancyIn;
import com.kandidato.persistence.entity.User;
import com.kandidato.persistence.entity.Vacancy;
import com.kandidato.persistence.entity.tag.Tag;
import com.kandidato.persistence.repository.vacancy.VacancyRepository;
import com.kandidato.security.model.CurrentProfile;
import com.kandidato.util.DozerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class VacancyManagerImpl implements VacancyManager {
    private static final Logger log = LoggerFactory.getLogger(VacancyManagerImpl.class);

    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private DozerUtil dozerUtil;

    @Override
    public VacancyDTO create(CurrentProfile currentUser, VacancyIn vacancyIn) {
        Vacancy entity = dozerUtil.map(vacancyIn, Vacancy.class);
        entity.setCreateTime(new Date());
        entity.setCreator(currentUser.getUser());
        entity.setState(VacancyState.OPEN);
        entity.setNumber("#" + entity.getCreateTime().hashCode());
        Vacancy vacancy = vacancyRepository.save(entity);

        for(String tagIn : vacancyIn.getTags()){
            Tag tag = new Tag();
            tag.setKeyword(tagIn);
            vacancy.getTags().add(tag);
        }
        vacancy = vacancyRepository.save(entity);
        return dozerUtil.map(vacancy, VacancyDTO.class);
    }

    @Override
    public VacancyDTO update(Long vacancyId, VacancyIn vacancy) {
        Vacancy entity = vacancyRepository.findOne(vacancyId);
        entity.setName(vacancy.getName());
        entity.setHot(vacancy.isHot());
        entity.setRequirements(vacancy.getRequirements());

        Vacancy newVacancy = vacancyRepository.save(entity);
        return dozerUtil.map(newVacancy, VacancyDTO.class);
    }

    @Override
    public VacancyDTO findById(long id) {
        log.debug("find vacancy by id: {}", id);
        Vacancy vacancy = vacancyRepository.findOne(id);
        log.debug("find vacancy by id: {} -  result: {}", id, vacancy);
        return dozerUtil.map(vacancy, VacancyDTO.class);
    }

    @Override
    public void remove(long id) {
        vacancyRepository.delete(id);
    }

    @Override
    public List<VacancyDTO> findByState(VacancyState state,  Long userId) {
        log.debug("find vacancy by state: {} and userId: {}", state, userId);
        List<Vacancy> vacancies = vacancyRepository.find(state, userId);
        log.debug("find vacancy by state: {} for user: {} -  result: {}", state, userId, vacancies.size());
        return dozerUtil.map(vacancies, VacancyDTO.class);
    }

    @Override
    public List<VacancyDTO> findByAuthor(long authorId) {
        log.debug("find vacancy by authorId: {}", authorId);
        List<Vacancy> vacancies = vacancyRepository.findByCreatorId(authorId);
        log.debug("find vacancy by authorId: {} -  result: {}", authorId, vacancies.size());
        return dozerUtil.map(vacancies, VacancyDTO.class);
    }

    @Override
    public VacancyDTO addTags(long vacancyId, TagsIn tagsIn) {
        log.debug("add tags to vacancy id: {}", vacancyId);
        Vacancy vacancy = vacancyRepository.findOne(vacancyId);

        for (String tag : tagsIn.getTags()) {
            Tag newTag = new Tag();
            newTag.setKeyword(tag);

            vacancy.getTags().add(newTag);
        }

        return dozerUtil.map(vacancyRepository.save(vacancy),
                VacancyDTO.class);
    }

    public void removeTag(long vacancyId, long tagId) {
        log.debug("remove tag {} from vacancy id: {}", tagId, vacancyId);
        Vacancy vacancy = vacancyRepository.findOne(vacancyId);
        for (Iterator<Tag> iterator = vacancy.getTags().iterator(); iterator.hasNext(); ) {
            Tag tag = iterator.next();
            if (tag.getId() == tagId) {
                iterator.remove();
                break;
            }
        }
        vacancyRepository.save(vacancy);
    }

    @Override
    public List<VacancyDTO> findAvailable(long candidateId, long creatorId, int page, int size) {
        log.debug("find vacancies available for candidate: {}", candidateId);
        List<Vacancy> vacancies = vacancyRepository.findNotConnectedToCandidate(candidateId, creatorId,
                new PageRequest(page, size));
        log.debug("found vacancies available for candidate: {} - result: {}", candidateId, vacancies.size());
        return dozerUtil.map(vacancies, VacancyDTO.class);
    }

    @Override
    public void archiveVacancy(long id) {
        Vacancy vacancy = vacancyRepository.findOne(id);
        vacancy.setState(VacancyState.CLOSED);
        vacancyRepository.save(vacancy);

    }

    @Override
    public List<VacancyDTO> findByState(VacancyState state, Long creatorId, String sortBy, String sortDirection, int page, int size) {
        List<Vacancy> vacancies = new ArrayList<>();
        if (state == VacancyState.NONE) {
            vacancies = vacancyRepository.find(creatorId,
                    new PageRequest(page, size)); //TODO adjust pagerequest
        } else {
            vacancies = vacancyRepository.find(state, creatorId,
                    new PageRequest(page, size)); //TODO adjust pagerequest}
        }
        log.debug("found vacancies with  state: {} - result: {}", state, vacancies.size());
        return dozerUtil.map(vacancies, VacancyDTO.class);
    }

    @Override
    public List<VacancyDTO> filterByStateAndName(VacancyState state, String name, User creator, String sortBy, Sort.Direction sortDirection, int page, int size) {
        final PageRequest pageRequest = new PageRequest(page, size, sortDirection, sortBy);

        List<Vacancy> vacancies;
        if(state == null && name == null){
            vacancies = vacancyRepository.find(creator.getId(), pageRequest);
        }else if (state == null){
            vacancies = vacancyRepository.findByNameContainingAndCreator(name, creator, pageRequest);
        }else if (name == null){
            vacancies = vacancyRepository.find(state, creator.getId(), pageRequest);
        }else{
            vacancies = vacancyRepository.findByNameContainingAndStateAndCreator(name, state, creator, pageRequest);
        }

        log.debug("Found vacancies with  state: {} and name: {} - result: {}", state, name, vacancies.size());
        return dozerUtil.map(vacancies, VacancyDTO.class);
    }
}
