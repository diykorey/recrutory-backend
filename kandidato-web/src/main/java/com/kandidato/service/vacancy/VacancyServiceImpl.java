package com.kandidato.service.vacancy;


import java.util.List;

/**
 * RESTful implementation of {@link com.kandidato.service.vacancy.VacancyService}.
 */
public class VacancyServiceImpl implements VacancyService {

    @Override
    public Vacancy findById(long id) {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public Vacancy remove(long id) {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public List<Vacancy> findByState(VacancyState state) {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public List<Vacancy> findByAuthor(long authorId) {
        throw new RuntimeException("Not yet implemented");
    }
}
