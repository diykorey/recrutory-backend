package com.kandidato.manager.search;

import com.kandidato.constants.FieldType;
import com.kandidato.model.dto.BaseCandidateDTO;
import com.kandidato.model.dto.SummaryCardDTO;
import com.kandidato.model.dto.TimelineFieldDTO;
import com.kandidato.model.dto.TimelineFieldTypeDTO;
import com.kandidato.persistence.repository.candidate.CandidateRepository;
import com.kandidato.persistence.repository.vacancy.VacancyRepository;
import com.kandidato.persistence.search.repository.ResumeSearchRepository;
import com.kandidato.persistence.search.model.ResumeDocument;
import com.kandidato.util.DozerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CandidateSearchManagerImpl implements CandidateSearchManager {
    private static final Logger LOG = LoggerFactory.getLogger(CandidateSearchManagerImpl.class);

    @Autowired
    private ResumeSearchRepository resumeSearchRepository;

    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private DozerUtil mapper;

    @Override
    public List<BaseCandidateDTO> findCandidateByVacancyId(String query, long vacancyId, long creatorId, int page, int size) {
        LOG.info("findCandidateByVacancyId called with id: {}", vacancyId);

       /* Vacancy vacancy = vacancyRepository.getOne(vacancyId);

        if (vacancy == null) {
            LOG.info("Vacancy with ID {} not found.", vacancyId);
            return new ArrayList<>();
        }
        LOG.info("Found vacancy with ID: {}", vacancy.getId());

        String requirements = vacancy.getRequirements();
        return findCandidateByRequirements(requirements, creatorId, page, size);
        */
        List<BaseCandidateDTO> temp = new ArrayList<>();
        BaseCandidateDTO candidateDTO = new BaseCandidateDTO();
        candidateDTO.setId(1L);
        SummaryCardDTO cardDTO = new SummaryCardDTO();
        TimelineFieldDTO name = new TimelineFieldDTO();
        TimelineFieldTypeDTO typeDTO = new TimelineFieldTypeDTO();
        typeDTO.setPriorityGroup(1);
        typeDTO.setPriority(1);
        typeDTO.setName(FieldType.NAME_SURNAME.name());
        name.setType(typeDTO);
        name.setFieldValue("Lucas");
        name.setId(1L);
        name.setFieldName(FieldType.NAME_SURNAME.name());
        cardDTO.setName(name);
        candidateDTO.setSummaryCard(cardDTO);
        temp.add(candidateDTO);

        return temp;
    }

    @Override
    public List<BaseCandidateDTO> findCandidates(String requirements, long creatorId, int page, int size) {
       /* QueryBuilder queryBuilder = QueryBuilders.filteredQuery(
                QueryBuilders.termQuery("text", requirements),
                QueryBuilders.termQuery("userId", creatorId));

        Page<ResumeDocument> resumes = resumeSearchRepository.search(queryBuilder, new PageRequest(page, size));
        List<Long> ids = getIdsFromResumes(resumes.getContent());

        return candidateRepository.findAll(ids).stream()
                .map(c -> {
                    BaseCandidateDTO dto = mapper.map(c, BaseCandidateDTO.class);
                    dto.setSummaryCard(buildSummaryCard(c.getTimelineFields()));

                    return dto;
                }).collect(toList());*/
        List<BaseCandidateDTO> temp = new ArrayList<>();
        BaseCandidateDTO candidateDTO = new BaseCandidateDTO();
        candidateDTO.setId(1L);
        SummaryCardDTO cardDTO = new SummaryCardDTO();
        TimelineFieldDTO name = new TimelineFieldDTO();
        TimelineFieldTypeDTO typeDTO = new TimelineFieldTypeDTO();
        typeDTO.setPriorityGroup(1);
        typeDTO.setPriority(1);
        typeDTO.setName(FieldType.NAME_SURNAME.name());
        name.setType(typeDTO);
        name.setFieldValue("Lucas");
        name.setId(1L);
        name.setFieldName(FieldType.NAME_SURNAME.name());
        cardDTO.setName(name);
        candidateDTO.setSummaryCard(cardDTO);
        temp.add(candidateDTO);

        return temp;
    }

    @Override
    public List<Long> findCandidateIdsByVacancyId(long vacancyId, long creatorId) {
       /* Vacancy vacancy = vacancyRepository.getOne(vacancyId);

        if (vacancy == null) {
            LOG.info("Vacancy with ID {} not found.", vacancyId);
            return new ArrayList<>();
        } else {
            LOG.info("Found vacancy with ID: {}", vacancy.getId());

            String requirements = vacancy.getRequirements();
            QueryBuilder queryBuilder = QueryBuilders.filteredQuery(
                    QueryBuilders.termQuery("text", requirements),
                    QueryBuilders.termQuery("userId", creatorId));

            Iterable<ResumeDocument> resumes = resumeSearchRepository.search(queryBuilder);

            return getIdsFromResumes(resumes);
        }*/
        List<Long> temp = new ArrayList<>();
        temp.add(1L);
        return temp;

    }

    private List<Long> getIdsFromResumes(Iterable<ResumeDocument> resumes) {
        /*List<Long> ids = new ArrayList<>();
        resumes.forEach(r -> ids.add(r.getCandidateId()));
        return ids;*/
        List<Long> temp = new ArrayList<>();
        temp.add(1L);
        return temp;
    }



}
