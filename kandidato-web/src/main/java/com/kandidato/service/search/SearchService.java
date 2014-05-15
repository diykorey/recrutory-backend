package com.kandidato.service.search;

import com.kandidato.exception.ResourceNotFoundException;
import com.kandidato.manager.search.CandidateSearchManager;
import com.kandidato.persistence.entity.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchService {

    @Autowired
    private CandidateSearchManager candidateSearchManager;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Transactional
    public List<Candidate> findByVacancyId(@RequestParam(value = "vacancyId", required = false) Long vacancyId,//
                                        @RequestParam(value = "requirements", required = false) String requirements) {

        List<Candidate> candidates = null;
        if(vacancyId != null){
            candidates = candidateSearchManager.findCandidateByVacancyId(vacancyId);
        } else if (requirements != null){
            candidates = candidateSearchManager.findCandidateByRequirements(requirements);
        }
        if (null == candidates || candidates.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return candidates;
    }
}
