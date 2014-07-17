package com.kandidato.manager.flow;

import com.kandidato.persistence.entity.Flow;
import com.kandidato.persistence.entity.Candidate;
import com.kandidato.persistence.entity.FlowAction;
import com.kandidato.persistence.entity.Vacancy;
import com.kandidato.persistence.repository.flow.ActionRepository;
import com.kandidato.persistence.repository.flow.FlowRepository;
import com.kandidato.persistence.repository.flow.query.FlowByVacancyQuery;
import com.kandidato.persistence.repository.candidate.CandidateRepository;
import com.kandidato.persistence.repository.vacancy.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class FlowManagerImpl implements FlowManager {

    @Autowired
    private FlowRepository flowRepository;

    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private CandidateRepository peoppleRepository;

    @Autowired
    private ActionRepository actionRepository;

    @Override
    public Flow create(long vacancyId, long candidateId) {
        Vacancy vacancy = vacancyRepository.findOne(vacancyId);
        Candidate candidate = peoppleRepository.findOne(candidateId);
        if (candidate == null || vacancy == null) {
            throw new IllegalArgumentException("Specify existing vacancies and candidate");
        }
        Flow newFlow = new Flow(vacancy, candidate);
        return this.flowRepository.save(newFlow);
    }

    @Override
    public Flow create(Flow flow) {
        return this.flowRepository.save(flow);
    }

    @Override
    public void remove(long id) {
        this.flowRepository.delete(id);
    }

    @Override
    public List<Flow> findByVacancy(final long vacancyId) {
        Collection<Flow> flows = this.flowRepository.query(new FlowByVacancyQuery(vacancyId));
        return new ArrayList<>(flows);
    }

    @Override
    public Flow find(long flowId) {
        return this.flowRepository.findOne(flowId);
    }

    @Override
    public List<Flow> findAll() {
        return this.flowRepository.findAll();
    }

    @Override
    public FlowAction createAction(FlowAction action) {
        return actionRepository.save(action);
    }
}
