package com.kandidato.manager.flow;

import com.kandidato.constants.FlowState;
import com.kandidato.model.dto.BaseCandidateDTO;
import com.kandidato.model.dto.CandidateDTO;
import com.kandidato.model.dto.FlowDTO;
import com.kandidato.model.dto.input.FlowIn;
import com.kandidato.persistence.entity.Candidate;
import com.kandidato.persistence.entity.Flow;
import com.kandidato.persistence.entity.FlowAction;
import com.kandidato.persistence.entity.Vacancy;
import com.kandidato.persistence.repository.candidate.CandidateRepository;
import com.kandidato.persistence.repository.flow.ActionRepository;
import com.kandidato.persistence.repository.flow.FlowRepository;
import com.kandidato.persistence.repository.user.UserRepository;
import com.kandidato.persistence.repository.vacancy.VacancyRepository;
import com.kandidato.util.DozerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static java.util.stream.Collectors.*;

@Component
public class FlowManagerImpl implements FlowManager {
    private static final Logger log = LoggerFactory.getLogger(FlowManagerImpl.class);
    @Autowired
    private FlowRepository flowRepository;

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DozerUtil dozerUtil;

    @Override
    public List<FlowDTO> create(List<FlowIn> flows) {
        return flows.stream().map((FlowIn f) -> create(f)).collect(toList());
    }

    private FlowDTO create(FlowIn flow) {
        Vacancy vacancy = vacancyRepository.findOne(flow.getVacancyId());
        Candidate candidate = candidateRepository.findOne(flow.getCandidateId());

        if (candidate == null || vacancy == null) {
            throw new IllegalArgumentException("Specify existing vacancy and candidate");
        }

        Flow newFlow = new Flow(vacancy, candidate);
        newFlow.setCreator(userRepository.findOne(1L));
        newFlow.setCreateTime(new Date());

        newFlow = flowRepository.save(newFlow);

        FlowAction flowAction = new FlowAction();
        flowAction.setState(FlowState.INIT);
        flowAction.setCreationTime(new Date());

        newFlow.getActions().add(flowAction);
        flowAction.setFlow(newFlow);

        actionRepository.save(flowAction);

        return dozerUtil.map(newFlow, FlowDTO.class);
    }

    @Override
    public void remove(long id) {
        flowRepository.delete(id);
    }

    @Override
    public List<FlowDTO> findByVacancy(final long vacancyId) {
        log.debug("find flows by vacancyid: {}", vacancyId);
        List<Flow> flows = flowRepository.findByVacancyId(vacancyId);
        log.debug("found {} flows by vacancyid: {}", flows.size(), vacancyId);
        List<FlowDTO> flowDtoList = new ArrayList<>();
        for(Flow flow:flows){
            Candidate candidate = flow.getCandidate();
            FlowDTO flowDTO = dozerUtil.map(flow, FlowDTO.class);
            flowDTO.getCandidate().setSummaryCard(CandidateDTO.buildSummaryCard(candidate.getTimelineFields()));
            flowDtoList.add(flowDTO);
        }
        return flowDtoList;
    }

    @Override
    public FlowDTO find(long flowId) {
        Flow flow = flowRepository.findOne(flowId);
        return dozerUtil.map(flow, FlowDTO.class);
    }

    @Override
    public List<FlowDTO> findFlowsByUser() {
        List<Flow> flows = flowRepository.findByCreatorId(1L);
        return dozerUtil.map(flows, FlowDTO.class);
    }

    @Override
    public List<FlowDTO> findByCandidate(final long candidateId) {
        log.debug("find flows by candidateId: {}", candidateId);
        List<Flow> flows = flowRepository.findByCandidateId(candidateId);
        log.debug("found {} flows by candidateId: {}", flows.size(), candidateId);
        return dozerUtil.map(flows, FlowDTO.class);
    }
}
