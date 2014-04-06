package com.kandidato.repository.vacancy;

import com.kandidato.entity.Vacancy;
import com.kandidato.repository.base.AbstractRepository;
import com.kandidato.repository.base.HibernateRepository;
import com.kandidato.repository.vacancy.query.VacancyQuery;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.Collection;

/**
 * Hibernate-based implementation of {@link com.kandidato.repository.vacancy.VacancyRepository}.
 */
@Repository
public class VacancyRepositoryImpl extends HibernateRepository<Vacancy, VacancyQuery> implements VacancyRepository {

    @Inject
    public VacancyRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Collection<Vacancy> query(VacancyQuery query) {
        return this.query(query, Vacancy.class);
    }
}
