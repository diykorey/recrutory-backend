package com.kandidato.repository.vacancy.query;

import com.kandidato.entity.Vacancy;
import org.hibernate.criterion.Criterion;

/**
 * Created by andriy on 4/3/14.
 */
public class VacancyByExampleQuery implements VacancyQuery {

    private final Vacancy example;

    public VacancyByExampleQuery(Vacancy example) {
        this.example = example;
    }

    @Override
    public Criterion toCriterion() {
        return null;
    }
}
