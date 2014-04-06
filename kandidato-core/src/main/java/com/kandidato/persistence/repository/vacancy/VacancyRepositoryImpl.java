package com.kandidato.persistence.repository.vacancy;

import com.kandidato.persistence.entity.Vacancy;
import com.kandidato.persistence.repository.base.HibernateRepository;
import com.kandidato.persistence.repository.vacancy.query.VacancyQuery;
import org.hibernate.SessionFactory;

import javax.inject.Inject;

/**
 * Hibernate-based implementation of {@link com.kandidato.persistence.repository.vacancy.VacancyRepository}.
 */
public class VacancyRepositoryImpl extends HibernateRepository<Vacancy, VacancyQuery> implements com.kandidato.persistence.repository.base.Repository<Vacancy, VacancyQuery> {

    public VacancyRepositoryImpl() {
        super(null, Vacancy.class);
    }
}
