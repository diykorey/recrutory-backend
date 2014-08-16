package com.kandidato.service.workflow;

import com.kandidato.constants.FlowState;
import com.kandidato.dto.FlowActionModel;
import com.kandidato.dto.FlowModel;
import com.kandidato.exception.ResourceNotFoundException;
import com.kandidato.manager.flow.FlowManager;
import com.kandidato.persistence.entity.Flow;
import com.kandidato.persistence.entity.FlowAction;
import com.kandidato.service.HttpAwareService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

//import javax.transaction.Transactional;
import java.util.*;

/**
 * RESTful implementation of {@link com.kandidato.service.workflow.WorkflowService}.
 */
@RequestMapping("/workflow")
@Controller
public class WorkflowServiceImpl extends HttpAwareService {

//    private static final Logger log = LoggerFactory.getLogger(WorkflowServiceImpl.class);

    @Autowired
    private FlowManager flowManager;

    //    @Override
    @ResponseBody
    @RequestMapping(value = "/forVacancy/{vacancyId}/{candidateId}", method = RequestMethod.POST, produces = "application/json")
    @Transactional
    public Flow create(@PathVariable long vacancyId, @PathVariable long candidateId) {
        return this.flowManager.create(vacancyId, candidateId);
    }

    //    @Override
    @RequestMapping(value = "/{flowId}", method = RequestMethod.DELETE)
    @Transactional
    public void remove(@PathVariable long flowId) {
        this.flowManager.remove(flowId);
    }

    //    @Override
    @RequestMapping(value = "/{flowId}", method = RequestMethod.GET, produces = "application/json")
    @Transactional
    @ResponseBody
    public Flow find(@PathVariable long flowId) {
        Flow flow = this.flowManager.find(flowId);
        if (null == flow) {
            throw new ResourceNotFoundException();
        }
        return flow;
    }

    //    @Override
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @Transactional
    public Flow create(@RequestBody Flow flow) {
        return this.flowManager.create(flow);
    }

    //    @Override
    @RequestMapping(value = "/byVacancy/{vacancyId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @Transactional
    public List<Flow> findFlowsByVacancy(@PathVariable long vacancyId) {
        return this.flowManager.findByVacancy(vacancyId);
    }

    @RequestMapping(value = "/flows", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @Transactional
    public List<FlowModel> findAll() {
        List<FlowModel> flows = new ArrayList<>();

        for (Flow flow : flowManager.findAll()) {
            FlowModel flowModel = new FlowModel();
            flowModel.setId(flow.getId());
            flowModel.setVacancyName(flow.getVacancy().getName());
            flowModel.setCandidateName(flow.getCandidate().getName() + " " + flow.getCandidate().getLastName());

            flows.add(flowModel);
        }
        return flows;
    }

    @RequestMapping(value = "/actions/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @Transactional
    public List<FlowActionModel> actions(@PathVariable Long id) {
        List<FlowActionModel> actionsModel = new ArrayList<>();
        Flow flow = flowManager.find(id);

        List<FlowAction> actions = flow.getActions();
        Collections.sort(actions, new Comparator<FlowAction>() {
            @Override
            public int compare(FlowAction o1, FlowAction o2) {
                return o2.getCreationTime().compareTo(o1.getCreationTime());
            }
        });

        for(FlowAction action : actions){
            FlowActionModel actionModel = new FlowActionModel();
            actionModel.setId(action.getId());
            actionModel.setState(action.getState().toString());
            actionModel.setDescription(action.getDescription());

            actionsModel.add(actionModel);
        }
        return actionsModel;
    }

    @RequestMapping(value = "/flow-action/{flowId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @Transactional
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public FlowActionModel addFlowAction(@RequestBody FlowActionModel action, @PathVariable long flowId) {
        Flow flow = flowManager.find(flowId);

        FlowAction flowAction = new FlowAction();
        flowAction.setState(FlowState.valueOf(action.getState()));
        flowAction.setDescription(action.getDescription());
        flowAction.setCreationTime(new Date());

        flow.getActions().add(flowAction);
        flowAction.setFlow(flow);

        flowManager.createAction(flowAction);

        return action;
    }

    @RequestMapping(value = "/flow-action/{actionId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @Transactional
    public FlowActionModel getFlowAction(@PathVariable long actionId) {
        return null;
    }
}
