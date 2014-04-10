package com.kandidato.persistence.repository.people;

import com.kandidato.persistence.entity.Person;
import com.kandidato.persistence.repository.base.HibernateRepository;
import com.kandidato.persistence.repository.people.query.PersonQuery;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * Created by andriy on 4/10/14.
 */
public class PeopleRepositoryImpl extends HibernateRepository<Person, PersonQuery> implements PeopleRepositoryCustom {

    @Autowired
    public PeopleRepositoryImpl(EntityManager entityManager) {
        super(entityManager, Person.class);
    }
}