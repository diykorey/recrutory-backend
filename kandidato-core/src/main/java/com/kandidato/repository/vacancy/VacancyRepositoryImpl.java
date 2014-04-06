package com.kandidato.repository.vacancy;

import com.kandidato.entity.Vacancy;
import com.kandidato.repository.base.HibernateRepository;
import com.kandidato.repository.vacancy.query.VacancyQuery;
import org.hibernate.SessionFactory;

import javax.inject.Inject;

/**
 * Hibernate-based implementation of {@link com.kandidato.repository.vacancy.VacancyRepository}.
 */
public class VacancyRepositoryImpl extends HibernateRepository<Vacancy, VacancyQuery> implements com.kandidato.repository.base.Repository<Vacancy, VacancyQuery> {

    @Inject
    public VacancyRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Vacancy.class);
    }
}
