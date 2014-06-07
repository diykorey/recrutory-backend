package com.kandidato.service.candidate;

import com.kandidato.exception.ResourceNotFoundException;
import com.kandidato.manager.candidate.CandidateManager;
import com.kandidato.persistence.entity.Candidate;
import com.kandidato.service.HttpAwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by andriy on 5/15/14.
 */
@Controller
@RequestMapping("/candidate")
public class CandidateServiceImpl extends HttpAwareService {

    @Autowired
    private CandidateManager manager;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @Transactional
    @ResponseStatus(value = HttpStatus.OK)
    public Candidate findById(@PathVariable long id) {
        Candidate result = this.manager.findById(id);
        if (null == result) {
            throw new ResourceNotFoundException();
        }
        return result;
    }

    @RequestMapping(value = "findByOwner/{ownerId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @Transactional
    @ResponseStatus(value = HttpStatus.OK)
    public List<Candidate> findByOwner(@PathVariable long ownerId) {
        return this.manager.findByOwner(ownerId);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    @Transactional
    @ResponseStatus(value = HttpStatus.OK)
    public void update(Candidate candidate) {
        this.manager.update(candidate);
    }
}
