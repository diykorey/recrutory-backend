package com.kandidato.service.workflow;

import com.kandidato.exception.ResourceNotFoundException;
import com.kandidato.manager.flow.FlowManager;
import com.kandidato.persistence.entity.Flow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

/**
 * RESTful implementation of {@link com.kandidato.service.workflow.WorkflowService}.
 */
@RequestMapping("/workflow")
@Controller
public class WorkflowServiceImpl {

    private static final Logger log = LoggerFactory.getLogger(WorkflowServiceImpl.class);

    @Autowired
    private FlowManager flowManager;

    //    @Override
    @ResponseBody
    @RequestMapping(value = "/forVacancy/{vacancyId}/{personId}", method = RequestMethod.GET, produces = "application/json" )
    @Transactional
    public Flow create(@PathVariable long vacancyId, @PathVariable long personId) {
        return this.flowManager.create(vacancyId, personId);
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

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void handleException(ResourceNotFoundException e) {
        log.debug("Resource not found {}", e);
    }
}
