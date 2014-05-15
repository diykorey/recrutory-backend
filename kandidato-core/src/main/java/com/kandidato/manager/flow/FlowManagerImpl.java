package com.kandidato.manager.flow;

import com.kandidato.persistence.entity.Flow;
import com.kandidato.persistence.entity.Candidate;
import com.kandidato.persistence.entity.Vacancy;
import com.kandidato.persistence.repository.flow.FlowRepository;
import com.kandidato.persistence.repository.flow.query.FlowQuery;
import com.kandidato.persistence.repository.candidate.CandidateRepository;
import com.kandidato.persistence.repository.vacancy.VacancyRepository;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by andriy on 4/8/14.
 */
@Component
public class FlowManagerImpl implements FlowManager {

    @Autowired
    private FlowRepository repository;

    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private CandidateRepository peoppleRepository;


    @Override
    public Flow create(long vacancyId, long candidateId) {
        Vacancy vacancy = vacancyRepository.findOne(vacancyId);
        Candidate candidate = peoppleRepository.findOne(candidateId);
        if (candidate == null || vacancy == null) {
            throw new IllegalArgumentException("Specify existing vacancies and candidate");
        }
        Flow newFlow = new Flow(vacancy, candidate);
        return this.repository.save(newFlow);
    }

    @Override
    public Flow create(Flow flow) {
        return this.repository.save(flow);
    }

    @Override
    public void remove(long id) {
        this.repository.delete(id);
    }

    @Override
    public List<Flow> findByVacancy(final long vacancyId) {
        Collection<Flow> flows = this.repository.query(new FlowQuery() {
            @Override
            public Criterion toCriterion() {
                return Restrictions.eq("vacancy.id", vacancyId);
            }
        });
        return new ArrayList<>(flows);
    }

    @Override
    public Flow find(long flowId) {
        return this.repository.findOne(flowId);
    }
}
