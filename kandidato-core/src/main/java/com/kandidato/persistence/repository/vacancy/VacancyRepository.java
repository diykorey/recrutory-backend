package com.kandidato.persistence.repository.vacancy;

import com.kandidato.persistence.entity.Vacancy;
import com.kandidato.persistence.repository.base.Repository;
import com.kandidato.persistence.repository.vacancy.query.VacancyQuery;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by andriy on 4/3/14.
 */
@org.springframework.stereotype.Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long>, Repository<Vacancy, VacancyQuery> {

}
