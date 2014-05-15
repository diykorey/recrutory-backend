package com.kandidato.manager.search;

import com.kandidato.persistence.entity.Candidate;
import com.kandidato.persistence.entity.Vacancy;
import com.kandidato.persistence.repository.people.CandidateRepository;
import com.kandidato.persistence.repository.vacancy.VacancyRepository;
import com.kandidato.persistence.search.ResumeSearchRepository;
import com.kandidato.persistence.search.elastic.ResumeDocument;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CandidateSearchManager {
    private static final Logger LOG = LoggerFactory.getLogger(CandidateSearchManager.class);

    @Autowired
    private ResumeSearchRepository resumeSearchRepository;

    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private CandidateRepository peopleRepository;

    public List<Candidate> findCandidateByVacancyId(long vacancyId){
        LOG.info("findCandidateByVacancyId called with id:" + vacancyId);

        Vacancy vacancy = vacancyRepository.getOne(vacancyId);

        if(vacancy == null){
            LOG.info("not found vacancy");
        }  else{
            LOG.info("found vacancy: " + vacancy.getId());
        }

        return findCandidateByRequirements(vacancy.getRequirements());
    }

    public List<Candidate> findCandidateByRequirements(String requirements){
        QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(requirements, "text");
        Iterable<ResumeDocument> resumes = resumeSearchRepository.search(queryBuilder);

        return peopleRepository.findAll(getResumesFromIds(resumes));
    }

    private List<Long> getResumesFromIds(Iterable<ResumeDocument> resumes) {
        List<Long> ids = new ArrayList<>();
        for(ResumeDocument resume : resumes){
            ids.add(resume.getCandidateId());
        }
        return ids;
    }

}
