package com.kandidato.repository.vacancy;

import com.kandidato.entity.Vacancy;
import com.kandidato.repository.base.AbstractRepository;
import com.kandidato.repository.vacancy.query.VacancyQuery;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.Collection;

/**
 * Hibernate-based implementation of {@link com.kandidato.repository.vacancy.VacancyRepository}.
 */
@Repository
public class VacancyRepositoryImpl extends AbstractRepository<Vacancy, VacancyQuery> implements VacancyRepository {

    @Inject
    public VacancyRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void add(Vacancy entity) {
        this.sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void remove(Vacancy entity) {
        this.sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public void update(Vacancy entity) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public Collection<Vacancy> query(VacancyQuery q) {
        return this.sessionFactory.getCurrentSession().createCriteria(Vacancy.class).add(q.toCriterion()).list();
    }
}
