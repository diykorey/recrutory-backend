package com.kandidato.manager.search;

import com.kandidato.constants.ResumeState;
import com.kandidato.persistence.entity.Person;
import com.kandidato.persistence.entity.Resume;
import com.kandidato.persistence.repository.people.PeopleRepository;
import com.kandidato.persistence.repository.resume.ResumeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ResumeManager {

    private static final Logger LOG = LoggerFactory.getLogger(ResumeManager.class);

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private PeopleRepository peopleRepository;

    public void createResume(String fileName, byte[] data){

        LOG.info("Creating resume fro file: " + fileName);
        Person person = new Person();
        person.setName(fileName);
        person.setLastName(fileName);
        person.setCreateTime(new Date());

        Person newPerson = peopleRepository.saveAndFlush(person);
        LOG.info("New person created with id: " + newPerson.getId());


        Resume resume = new Resume();
        resume.setState(ResumeState.NEW);
        resume.setData(data);
        resume.setResumeOwner(newPerson);

        Resume newResume = resumeRepository.saveAndFlush(resume);
        LOG.info("New resume created with id: " + newResume.getId());
    }
}
