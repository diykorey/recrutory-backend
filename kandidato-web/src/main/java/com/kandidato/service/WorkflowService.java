package com.kandidato.service;

import com.kandidato.exception.ResourceNotFoundException;
import com.kandidato.manager.flow.FlowActionsManager;
import com.kandidato.manager.flow.FlowManager;
import com.kandidato.model.dto.FlowActionDTO;
import com.kandidato.model.dto.FlowDTO;
import com.kandidato.model.dto.input.DescriptionIn;
import com.kandidato.model.dto.input.FlowActionIn;
import com.kandidato.model.dto.input.FlowsIn;
import com.wordnik.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/workflow")
@Api(value = "workflow", description = "WorkflowService operations with flows and flowactions", produces = "application/json")
public class WorkflowService extends HttpAwareService {

    private static final Logger LOG = LoggerFactory.getLogger(WorkflowService.class);

    @Autowired
    private FlowManager flowManager;

    @Autowired
    private FlowActionsManager flowActionsManager;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(value = HttpStatus.CREATED)
    public List<FlowDTO> create(@RequestBody @Valid FlowsIn flows) {
        return flowManager.create(flows.getFlows());
    }

    @RequestMapping(value = "/{flowId}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long flowId) {
        flowManager.remove(flowId);
    }

    @RequestMapping(value = "/{flowId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public FlowDTO find(@PathVariable long flowId) {
        FlowDTO flow = flowManager.find(flowId);
        if (null == flow) {
            throw new ResourceNotFoundException();
        }
        return flow;
    }

    @RequestMapping(value = "/byVacancy/{vacancyId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<FlowDTO> findFlowsByVacancy(@PathVariable long vacancyId) {
        return flowManager.findByVacancy(vacancyId);
    }

    @RequestMapping(value = "/byCandidate/{candidateId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<FlowDTO> findFlowsByCandidate(@PathVariable long candidateId) {
        return flowManager.findByCandidate(candidateId);
    }

    @RequestMapping(value = "/forUser", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<FlowDTO> findFlowsByUser() {
        return flowManager.findFlowsByUser();
    }

    @RequestMapping(value = "/{flowId}/actions", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<FlowActionDTO> actions(@PathVariable Long flowId) {
        return flowActionsManager.actions(flowId);
    }

    @RequestMapping(value = "/action", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public FlowActionDTO addAction(@RequestBody @Valid FlowActionIn action) {
        return flowActionsManager.create(action);
    }

    @RequestMapping(value = "/action/{actionId}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public FlowActionDTO updateDescription(@PathVariable Long actionId, @RequestBody @Valid DescriptionIn description) {
        return flowActionsManager.updateDescription(actionId, description);
    }
}
