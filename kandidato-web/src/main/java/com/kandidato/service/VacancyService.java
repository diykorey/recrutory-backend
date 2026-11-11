package com.kandidato.service;

import com.kandidato.constants.VacancyState;
import com.kandidato.exception.ResourceNotFoundException;
import com.kandidato.manager.vacancy.VacancyManager;
import com.kandidato.model.dto.VacancyDTO;
import com.kandidato.model.dto.input.TagsIn;
import com.kandidato.model.dto.input.VacancyIn;
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
@RequestMapping("/vacancy")
@Api(value = "vacancy", description = "VacancyService operations with vacancies", produces = "application/json")
public class VacancyService extends HttpAwareService {

    private static final Logger log = LoggerFactory.getLogger(VacancyService.class);

    @Autowired
    private VacancyManager vacancyManager;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    public VacancyDTO create(@InjectUser CurrentProfile user, @RequestBody @Valid VacancyIn vacancy) {
        return vacancyManager.create(user, vacancy);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public VacancyDTO update(@PathVariable("id") long id, @RequestBody @Valid VacancyIn vacancy) {
        return vacancyManager.update(id, vacancy);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public VacancyDTO findById(@InjectUser CurrentProfile user, @PathVariable("id") long id) {
        Long userId = user.getId();
        VacancyDTO vacancy = vacancyManager.findById(id);
        if (null == vacancy) {
            throw new ResourceNotFoundException();
        }
        return vacancy;
    }

    @RequestMapping(value = "/byState/{state}/paging", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<VacancyDTO> findByState(@InjectUser CurrentProfile user,
                                        @PathVariable VacancyState state,
                                        @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
                                        @RequestParam(value = "sortDirection", defaultValue = "ASC") String direction,
                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "10") int size) {
        log.debug("findByState: {}", state);
        return vacancyManager.findByState(state, user.getId(), sortBy, direction, page, size);
    }


    @RequestMapping(value = "/byState/{state}", method = {RequestMethod.GET, RequestMethod.OPTIONS}, produces = "application/json")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<VacancyDTO> findByState(
            @InjectUser CurrentProfile user, @PathVariable VacancyState state) {
        log.debug("findByState: {}", state);
        return vacancyManager.findByState(state, user.getId());
    }

    @RequestMapping(value = "/byAuthor/{authorId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @Transactional
    @ResponseStatus(value = HttpStatus.OK)
    public List<VacancyDTO> findByAuthor(@PathVariable long authorId) {
        List<VacancyDTO> vacancies = vacancyManager.findByAuthor(authorId);
        if (vacancies.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return vacancies;
    }

    @RequestMapping(value = "/{vacancyId}/tags", method = RequestMethod.POST,
            produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public VacancyDTO addTags(@PathVariable("vacancyId") long vacancyId, @Valid @RequestBody TagsIn tagsIn) {
        return vacancyManager.addTags(vacancyId, tagsIn);
    }

    @RequestMapping(value = "/{vacancyId}/tags/{tagId}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void remove(@PathVariable("vacancyId") long vacancyId, @PathVariable("tagId") long tagId) {
        vacancyManager.removeTag(vacancyId, tagId);
    }


    @RequestMapping(value = "/findAvailable", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @Transactional
    @ResponseStatus(value = HttpStatus.OK)
    public List<VacancyDTO> findAvailable(@RequestParam(value = "search", defaultValue = "", required = false) String search,
                                          @RequestParam(value = "candidateId", required = true) long candidateId,
                                          @RequestParam(value = "page", defaultValue = "0") int page,
                                          @RequestParam(value = "size", defaultValue = "10") int size) {
        long creatorId = 1L;
        return vacancyManager.findAvailable(candidateId, creatorId, page, size);
    }

    @RequestMapping(value = "/findRecommended", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @Transactional
    @ResponseStatus(value = HttpStatus.OK)
    public List<VacancyDTO> findRecommended(@RequestParam(value = "search", defaultValue = "", required = false) String search,
                                            @RequestParam(value = "candidateId", required = true) long candidateId,
                                            @RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "size", defaultValue = "10") int size) {
        long creatorId = 1L;
        return vacancyManager.findAvailable(candidateId, creatorId, page, size);
    }

    @RequestMapping(value = "/{vacancyId}/archive", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void archiveVacancy(@PathVariable("vacancyId") long vacancyId) {
        vacancyManager.archiveVacancy(vacancyId);
    }

    //TODO does it p[ossible merge name/state in some query object?
    @RequestMapping(value = "/paging", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<VacancyDTO> filtering(@InjectUser CurrentProfile user,
                                        @RequestParam(value = "name", required = false) String name,
                                        @RequestParam(value = "state", required = false) VacancyState state,
                                        @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
                                        @RequestParam(value = "sortDirection", defaultValue = "ASC") Sort.Direction direction,
                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "10") int size) {
        log.debug("Filtering by name: {} and state: {}.", name, state);
        return vacancyManager.filterByStateAndName(state, name, user.getUser(), sortBy, direction, page, size);
    }

}
