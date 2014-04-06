package com.kandidato.repository.vacancy;

import com.kandidato.entity.Vacancy;
import com.kandidato.repository.base.Repository;
import com.kandidato.repository.vacancy.query.VacancyQuery;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by andriy on 4/3/14.
 */
public interface VacancyRepository extends JpaRepository<Vacancy, Long>, Repository<Vacancy, VacancyQuery> {

}
