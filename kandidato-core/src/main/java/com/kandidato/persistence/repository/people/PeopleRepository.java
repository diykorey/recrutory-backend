package com.kandidato.persistence.repository.people;

import com.kandidato.persistence.entity.Person;
import com.kandidato.persistence.repository.people.query.PersonQuery;
import com.kandidato.persistence.repository.query.HibernateQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by andriy on 4/10/14.
 */
@Repository
public interface PeopleRepository extends JpaRepository<Person, Long>, com.kandidato.persistence.repository.base.Repository<Person, PersonQuery>{
}
