package com.kandidato.persistence.repository.vacancy.query;

import com.kandidato.persistence.entity.Vacancy;
import com.kandidato.persistence.repository.query.ByExmpleHibernateQuery;

/**
 * Created by andriy on 4/3/14.
 */
public class VacancyByExampleQuery extends ByExmpleHibernateQuery<Vacancy> implements VacancyQuery {
    public VacancyByExampleQuery(Vacancy example) {
        super(example);
    }
}
