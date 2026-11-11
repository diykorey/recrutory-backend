package com.kandidato.service;

import com.kandidato.manager.search.CandidateSearchManager;
import com.kandidato.model.dto.mark.HasMarks;
import com.kandidato.model.dto.mark.Mark;
import com.kandidato.model.dto.mark.MarkedIdentifier;
import com.kandidato.model.dto.mark.MatchMark;
import com.kandidato.security.auth.InjectUser;
import com.kandidato.security.model.CurrentProfile;
import com.wordnik.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Controller
@RequestMapping("/mark")
@Api(value = "mark", description = "VacancyMarkServiceImpl operations with vacancies marks", produces = "application/json")
public class VacancyMarkService {

    @Autowired
    private CandidateSearchManager candidateSearchManager;

    @RequestMapping(value = "/all", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public Set<HasMarks> getMarks(@InjectUser CurrentProfile user, @RequestBody Set<Long> vacancyIds) {
        return vacancyIds.stream()
                .map(vacancyId -> calculateVacancyMarks(vacancyId, user.getId()))
                .collect(toSet());
    }

    private HasMarks calculateVacancyMarks(Long vacancyId, Long userId) {
        List<Long> ids = candidateSearchManager.findCandidateIdsByVacancyId(vacancyId, userId);

        Set<Mark> marks = ids.stream()
                .map(id -> new MatchMark(vacancyId, id))
                .collect(toSet());

        return new MarkedIdentifier(vacancyId, marks);
    }
}
