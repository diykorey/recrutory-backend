package com.kandidato.service.search;

import com.kandidato.constants.VacancyState;
import com.kandidato.exception.ResourceNotFoundException;
import com.kandidato.manager.search.vacancy.VacancySearchManager;
import com.kandidato.model.dto.BaseUserDTO;
import com.kandidato.model.dto.VacancyDTO;
import com.kandidato.security.auth.InjectUser;
import com.kandidato.security.model.CurrentProfile;
import com.wordnik.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by andriy on 07.05.15.
 */
@Controller
@RequestMapping("/search/vacancy")
@Api(value = "vacancy_search", description = "Full text search for vacancies", produces = "application/json")
public class VacancySearchService {

    @Autowired
    private VacancySearchManager vacancySearchManager;

    @RequestMapping(value = "byKeywords", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public Set<VacancyDTO> findVacanciesByKeywords(@InjectUser CurrentProfile user,
                                                   @RequestParam(value = "query", required = true) String searchQuery,
                                                   @RequestParam(value = "page", defaultValue = "0") int page,
                                                   @RequestParam(value = "size", defaultValue = "10") int size) {
        //Optional<Set<VacancyDTO>> vacancies = this.vacancySearchManager.findVacancies(searchQuery, user.getId(), page, size);
        //return vacancies.orElse(new HashSet<>());
        Set<VacancyDTO> temp = new HashSet<>();
        VacancyDTO vacancyDTO = new VacancyDTO();
        vacancyDTO.setId(1L);
        vacancyDTO.setName("test");
        vacancyDTO.setNumber("1");
        vacancyDTO.setRequirements("Some requirements");
        vacancyDTO.setState(VacancyState.OPEN);
        temp.add(vacancyDTO);

        return temp;
    }

}
