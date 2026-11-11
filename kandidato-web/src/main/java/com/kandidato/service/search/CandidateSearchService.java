package com.kandidato.service.search;

import com.kandidato.exception.ResourceNotFoundException;
import com.kandidato.manager.candidate.CandidateManager;
import com.kandidato.manager.search.CandidateSearchManager;
import com.kandidato.manager.search.vacancy.VacancySearchManager;
import com.kandidato.model.dto.BaseCandidateDTO;
import com.kandidato.model.dto.CandidateDTO;
import com.kandidato.model.dto.VacancyDTO;
import com.kandidato.security.auth.InjectUser;
import com.kandidato.security.model.CurrentProfile;
import com.kandidato.util.DozerUtil;
import com.wordnik.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by andriy on 07.05.15.
 */
@Controller
@RequestMapping("/search/candidate")
@Api(value = "candidate_search", description = "Full text search for candidates", produces = "application/json")
public class CandidateSearchService {

    @Autowired
    private CandidateManager candidateManager;

    private CandidateSearchManager candidateSearchManager;

    @Autowired
    private DozerUtil mapper;

    @RequestMapping(value = "byKeywords", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<BaseCandidateDTO> findKandidateByKeywords(@InjectUser CurrentProfile user,
                                                          @RequestParam(value = "query", required = true) String searchQuery,
                                                          @RequestParam(value = "page", defaultValue = "0") int page,
                                                          @RequestParam(value = "size", defaultValue = "10") int size) {
        List<BaseCandidateDTO> candidates = candidateSearchManager.findCandidates(searchQuery, user.getId(), page, size);
        return candidates;
    }

}
