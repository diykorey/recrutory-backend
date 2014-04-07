package com.kandidato.persistence.repository.vacancy;

import com.kandidato.persistence.entity.Vacancy;
import com.kandidato.persistence.repository.base.HibernateRepository;
import com.kandidato.persistence.repository.vacancy.query.VacancyQuery;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * Hibernate-based implementation of {@link com.kandidato.persistence.repository.vacancy.VacancyRepository}.
 */
public class VacancyRepositoryImpl extends HibernateRepository<Vacancy, VacancyQuery> implements VacancyRepositoryCustom {

    @Autowired
    public VacancyRepositoryImpl(EntityManager entityManager) {
        super(entityManager, Vacancy.class);
    }
}
