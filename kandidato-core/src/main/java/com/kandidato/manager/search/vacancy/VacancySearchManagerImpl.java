package com.kandidato.manager.search.vacancy;

import com.kandidato.model.dto.VacancyDTO;
import com.kandidato.persistence.entity.Vacancy;
import com.kandidato.persistence.repository.vacancy.VacancyRepository;
import com.kandidato.persistence.search.repository.VacancySearchRepository;
import com.kandidato.persistence.search.model.VacancyDocument;
import com.kandidato.util.DozerUtil;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class VacancySearchManagerImpl implements VacancySearchManager {

    @Autowired
    private VacancySearchRepository vacancySearchRepository;

    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private DozerUtil mapper;

    @Override
    public Optional<Set<VacancyDTO>> findVacancies(String query, long creatorId, int page, int size) {

        QueryBuilder queryBuilder = QueryBuilders.filteredQuery(
                QueryBuilders.termQuery("requirements", query),
                QueryBuilders.termQuery("userId", creatorId));

        Iterable<VacancyDocument> searchResult = vacancySearchRepository.search(queryBuilder);
        Set<String> vacancyNumbers = getVacancyNumbers(searchResult);
        List<Vacancy> vacancies = vacancyRepository.findByCreator_IdAndNumberIn(creatorId, vacancyNumbers, new PageRequest(page, size));

        return Optional.of(new HashSet<>(mapper.map(vacancies, VacancyDTO.class)));
    }

    private Set<String> getVacancyNumbers(Iterable<VacancyDocument> vacancies) {
        Set<String> ids = new HashSet<>();
        vacancies.forEach(v -> ids.add(v.getNumber()));
        return ids;
    }
}
