package com.kandidato.persistence.repository.vacancy.query;

import com.kandidato.persistence.entity.Vacancy;
import com.kandidato.persistence.repository.query.ByExampleHibernateQuery;

/**
 * Created by andriy on 4/3/14.
 */
public class VacancyByExampleQuery extends ByExampleHibernateQuery<Vacancy> implements VacancyQuery {
    public VacancyByExampleQuery(Vacancy example) {
        super(example);
    }
}
