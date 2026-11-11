package com.kandidato.manager.search.vacancy;

import com.kandidato.constants.SearchIndexState;
import com.kandidato.persistence.entity.Vacancy;
import com.kandidato.persistence.repository.vacancy.VacancyRepository;
import com.kandidato.persistence.search.repository.VacancySearchRepository;
import com.kandidato.persistence.search.model.VacancyDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class VacancyIndexerImpl implements VacancyIndexer {

    private static final Logger LOG = LoggerFactory.getLogger(VacancyIndexerImpl.class);

    private final VacancyRepository vacancyRepository;

    private final VacancySearchRepository vacancySearchRepository;

    @Autowired
    public VacancyIndexerImpl(VacancyRepository vacancyRepository, VacancySearchRepository vacancySearchRepository) {
        this.vacancyRepository = vacancyRepository;
        this.vacancySearchRepository = vacancySearchRepository;
    }

    @Override
    public void indexVacancies() {
        this.vacancyRepository.findBySearchStateIn(Arrays.asList(SearchIndexState.NEW, SearchIndexState.OLD), new PageRequest(0, 50))
                .forEach((vacancy) -> addToIndex(vacancy));
    }

    private void addToIndex(Vacancy vacancy) {
        try {
            vacancySearchRepository.index(vacancyToDocument(vacancy));
            vacancy.setSearchState(SearchIndexState.INDEXED);
            LOG.info("Vacancy with ID {} was indexed successfully.", vacancy.getId());
        } catch (Exception e) {
            LOG.error("Vacancy with ID {} indexing failed because of the following exception: {} ",
                    vacancy.getId(), e.getMessage());
            vacancy.setSearchState(SearchIndexState.ERROR);
        }
        vacancyRepository.saveAndFlush(vacancy);
    }


    private VacancyDocument vacancyToDocument(Vacancy vacancy) {
        return new VacancyDocument(vacancy.getNumber(), vacancy.getName(), vacancy.getCreator().getId(), vacancy.isHot(), vacancy.getTags().stream().map(t -> t.getKeyword()).collect(Collectors.toSet()), vacancy.getRequirements());
    }
}
