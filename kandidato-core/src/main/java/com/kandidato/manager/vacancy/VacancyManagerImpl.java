package com.kandidato.manager.vacancy;

import com.google.common.collect.Iterables;
import com.kandidato.constants.VacancyState;
import com.kandidato.persistence.entity.Vacancy;
import com.kandidato.persistence.repository.vacancy.VacancyRepository;
import com.kandidato.persistence.repository.vacancy.query.RemoveVacancyQuery;
import com.kandidato.persistence.repository.vacancy.query.VacancyByAuthorQuery;
import com.kandidato.persistence.repository.vacancy.query.VacancyByExampleQuery;
import com.kandidato.persistence.repository.vacancy.query.VacancyByIdQuery;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by andriy on 4/3/14.
 */
@Component
public class VacancyManagerImpl implements VacancyManager {

    @Inject
    private VacancyRepository repository;


    @Override
    public Vacancy findById(long id) {
        return Iterables.getOnlyElement(repository.query(new VacancyByIdQuery(id)));
    }

    @Override
    public void remove(long id) {
        this.repository.delete(id);
    }

    @Override
    public List<Vacancy> findByState(VacancyState state) {
        Vacancy example = new Vacancy();
        example.setState(state);
        Collection<Vacancy> result = repository.query(new VacancyByExampleQuery(example));
        return new ArrayList<>(result);
    }

    @Override
    public List<Vacancy> findByAuthor(long authorId) {
        Collection<Vacancy> result = repository.query(new VacancyByAuthorQuery(authorId));
        return new ArrayList<>(result);
    }
}
