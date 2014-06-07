package com.kandidato.service.workflow;

import com.kandidato.exception.ResourceNotFoundException;
import com.kandidato.manager.flow.FlowManager;
import com.kandidato.persistence.entity.Flow;
import com.kandidato.service.HttpAwareService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * RESTful implementation of {@link com.kandidato.service.workflow.WorkflowService}.
 */
@RequestMapping("/workflow")
@Controller
public class WorkflowServiceImpl extends HttpAwareService {

    private static final Logger log = LoggerFactory.getLogger(WorkflowServiceImpl.class);

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
    public List<Flow> list() {
        List<Flow> flows = new ArrayList<>();

        Flow f1 = new Flow();
        f1.setId(100L);
        f1.setActive(true);
        f1.setVacancy(null);
        f1.setCandidate(null);
        f1.setCreateTime(new Date());

        Flow f2 = new Flow();
        f2.setId(200L);
        f2.setActive(true);
        f2.setVacancy(null);
        f2.setCandidate(null);
        f2.setCreateTime(new Date());

        Flow f3 = new Flow();
        f3.setId(300L);
        f3.setActive(true);
        f3.setVacancy(null);
        f3.setCandidate(null);
        f3.setCreateTime(new Date());

        Flow f4 = new Flow();
        f4.setId(400L);
        f4.setActive(true);
        f4.setVacancy(null);
        f4.setCandidate(null);
        f4.setCreateTime(new Date());

        Flow f5 = new Flow();
        f5.setId(500L);
        f5.setActive(true);
        f5.setVacancy(null);
        f5.setCandidate(null);
        f5.setCreateTime(new Date());

        Flow f6 = new Flow();
        f6.setId(600L);
        f6.setActive(true);
        f6.setVacancy(null);
        f6.setCandidate(null);
        f6.setCreateTime(new Date());

        flows.add(f1);
        flows.add(f2);
        flows.add(f3);
        flows.add(f4);
        flows.add(f5);
        flows.add(f6);

        return flows;
    }
}
