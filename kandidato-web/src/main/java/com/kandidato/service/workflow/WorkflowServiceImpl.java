package com.kandidato.service.workflow;

import com.kandidato.constants.FlowState;
import com.kandidato.dto.FlowActionModel;
import com.kandidato.dto.FlowModel;
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
    public List<FlowModel> list() {
        List<FlowModel> flows = new ArrayList<>();

        FlowModel f1 = new FlowModel();
        f1.setId(1L);
        f1.setVacancyName("Java 1");
        f1.setCandidateName("Lisa");

        FlowModel f2 = new FlowModel();
        f2.setId(2L);
        f2.setVacancyName("Java 2");
        f2.setCandidateName("Marge");

        FlowModel f3 = new FlowModel();
        f3.setId(3L);
        f3.setVacancyName("Java 2");
        f3.setCandidateName("Bart");

        FlowModel f4 = new FlowModel();
        f4.setId(4L);
        f4.setVacancyName("Java 1");
        f4.setCandidateName("Homer");

        FlowModel f5 = new FlowModel();
        f5.setId(5L);
        f5.setVacancyName("Java 2");
        f5.setCandidateName("Homer");

        FlowModel f6 = new FlowModel();
        f6.setId(6L);
        f6.setVacancyName("Java 3");
        f6.setCandidateName("Bart");

        flows.add(f1);
        flows.add(f2);
        flows.add(f3);
        flows.add(f4);
        flows.add(f5);
        flows.add(f6);

        return flows;
    }

    @RequestMapping(value = "/actions/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @Transactional
    public List<FlowActionModel> actions(@PathVariable Long id) {
        List<FlowActionModel> actions = new ArrayList<>();

        if(id % 2 == 0){
            FlowActionModel action1 = new FlowActionModel();
            action1.setId(1);
            action1.setState(FlowState.CONTACT.toString());
            action1.setDescription("Desciption for 1");

            FlowActionModel action2 = new FlowActionModel();
            action2.setId(2);
            action2.setState(FlowState.INTERVIEW.toString());
            action2.setDescription("Desciption for 2");

            actions.add(action1);
            actions.add(action2);
        }      else{
            FlowActionModel action3 = new FlowActionModel();
            action3.setId(3);
            action3.setState(FlowState.CONTACT.toString());
            action3.setDescription("Desciption for 3");

            FlowActionModel action4 = new FlowActionModel();
            action4.setId(4);
            action4.setState(FlowState.INTERVIEW.toString());
            action4.setDescription("Desciption for 4");

            FlowActionModel action5 = new FlowActionModel();
            action5.setId(5);
            action5.setState(FlowState.HIRED.toString());
            action5.setDescription("Desciption for 5");

            actions.add(action3);
            actions.add(action4);
            actions.add(action5);
        }

        return actions;
    }
}
