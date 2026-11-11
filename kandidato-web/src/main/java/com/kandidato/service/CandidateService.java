package com.kandidato.service;

import com.kandidato.constants.VacancyState;
import com.kandidato.exception.ResourceNotFoundException;
import com.kandidato.manager.candidate.CandidateManager;
import com.kandidato.manager.search.CandidateSearchManager;
import com.kandidato.model.dto.*;
import com.kandidato.model.dto.input.CandidateIn;
import com.kandidato.model.dto.input.TagsIn;
import com.kandidato.security.auth.InjectUser;
import com.kandidato.security.model.CurrentProfile;
import com.wordnik.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/candidate")
@Api(value = "candidate", description = "CandidateServiceImpl Operations with candidate", produces = "application/json")
public class CandidateService extends HttpAwareService {
    private static final Logger log = LoggerFactory.getLogger(CandidateService.class);
    @Autowired
    private CandidateManager candidateManager;

    @Autowired
    private CandidateSearchManager candidateSearchManager;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @Transactional
    @ResponseStatus(value = HttpStatus.OK)
    public BaseCandidateDTO findById(@InjectUser CurrentProfile user, @PathVariable long id) {
        BaseCandidateDTO result = candidateManager.getCandidate(id);
        if (null == result) {
            throw new ResourceNotFoundException();
        }
        return result;
    }

    @RequestMapping(value = "profile/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @Transactional
    @ResponseStatus(value = HttpStatus.OK)
    public CandidateDTO findProfileById(@InjectUser CurrentProfile user, @PathVariable long id) {
        CandidateDTO result = this.candidateManager.getCandidateProfile(id);
        if (null == result) {
            throw new ResourceNotFoundException();
        }
        return result;
    }

    @RequestMapping(value = "/findByOwner/{ownerId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @Transactional
    @ResponseStatus(value = HttpStatus.OK)
    public List<BaseCandidateDTO> findByOwner(@InjectUser CurrentProfile user, @PathVariable long ownerId) {
        return this.candidateManager.findByOwner(ownerId);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    @Transactional
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@Valid CandidateDTO candidate) {
        this.candidateManager.update(candidate);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    @Transactional
    @ResponseStatus(value = HttpStatus.CREATED)
    public BaseCandidateDTO create(@InjectUser CurrentProfile user, @RequestBody @Valid CandidateIn candidate) {
        return candidateManager.create(user, candidate);
    }

    @RequestMapping(value = "/findAvailable", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @Transactional
    @ResponseStatus(value = HttpStatus.OK)
    public List<BaseCandidateDTO> findAvailable(@InjectUser CurrentProfile user, @RequestParam(value = "search", defaultValue = "", required = false) String search,
                                                @RequestParam(value = "vacancyId", required = true) long vacancyId,
                                                @RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "size", defaultValue = "10") int size) {

        return candidateManager.findAvailable(vacancyId, user.getId(), page, size);
    }

    @RequestMapping(value = "/findRecommended", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @Transactional
    @ResponseStatus(value = HttpStatus.OK)
    public List<BaseCandidateDTO> findRecommended(@InjectUser CurrentProfile user, @RequestParam(value = "search", defaultValue = "", required = false) String query,
                                                  @RequestParam(value = "vacancyId", required = true) long vacancyId,
                                                  @RequestParam(value = "page", defaultValue = "0") int page,
                                                  @RequestParam(value = "size", defaultValue = "10") int size) {
        return candidateSearchManager.findCandidateByVacancyId(query, vacancyId, user.getId(), page, size);
    }

    @RequestMapping(value = "/{candidateId}/addfield", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public BaseCandidateDTO addField(@InjectUser CurrentProfile user, @PathVariable("candidateId") long candidateId, @Valid @RequestBody TimelineFieldDTO field) {
        return candidateManager.addField(user.getId(), candidateId, field);
    }


    @RequestMapping(value = "/{fieldId}/updatefield", method = RequestMethod.POST,
            produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public BaseCandidateDTO updateField(@InjectUser CurrentProfile user, @PathVariable("fieldId") long fieldId, @Valid @RequestBody TimelineFieldDTO field) {
        return candidateManager.updateField(fieldId, field);
    }

    @RequestMapping(value = "/removefield/{fieldId}", method = RequestMethod.DELETE)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public void removeField(@InjectUser CurrentProfile user, @PathVariable("fieldId") long fieldId) {
        candidateManager.removeField(fieldId);
    }

    @RequestMapping(value = "/findFieldTypes", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @Transactional
    @ResponseStatus(value = HttpStatus.OK)
    public List<TimelineFieldTypeDTO> findAvailable(@InjectUser CurrentProfile user) {
        return candidateManager.findFieldTypes(user.getId());
    }


    @RequestMapping(value = "/{candidateId}/tags", method = RequestMethod.POST,
            produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public BaseCandidateDTO addTags(@InjectUser CurrentProfile user, @PathVariable("candidateId") long candidateId, @Valid @RequestBody TagsIn tagsIn) {
        return candidateManager.addTags(candidateId, tagsIn);
    }

    @RequestMapping(value = "/{candidateId}/tags/{tagId}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void remove(@InjectUser CurrentProfile user, @PathVariable("candidateId") long candidateId, @PathVariable("tagId") long tagId) {
        candidateManager.removeTag(candidateId, tagId);
    }

    //TODO does it p[ossible merge name/state in some query object?
    @RequestMapping(value = "/paging", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<BaseCandidateDTO> filtering(@InjectUser CurrentProfile user,
                                      @RequestParam(value = "name", required = false) String name,
                                      @RequestParam(value = "email", required = false) String email,
                                      @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
                                      @RequestParam(value = "sortDirection", defaultValue = "ASC") Sort.Direction direction,
                                      @RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "size", defaultValue = "10") int size) {
        log.debug("Filtering by name: {} and email: {}.", name, email);
        return candidateManager.filterByNameAndEmail(name, email, user.getUser(), sortBy, direction, page, size);
    }
}
