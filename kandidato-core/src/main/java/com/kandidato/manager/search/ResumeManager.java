package com.kandidato.manager.search;

import com.kandidato.constants.ResumeState;
import com.kandidato.persistence.entity.Candidate;
import com.kandidato.persistence.entity.Resume;
import com.kandidato.persistence.repository.candidate.CandidateRepository;
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
    private CandidateRepository peopleRepository;

    public void createResume(String fileName, byte[] data){

        LOG.info("Creating resume fro file: " + fileName);
        Candidate candidate = new Candidate();
        candidate.setName(fileName);
        candidate.setLastName(fileName);
        candidate.setCreateTime(new Date());

        Candidate newCandidate = peopleRepository.saveAndFlush(candidate);
        LOG.info("New candidate created with id: " + newCandidate.getId());


        Resume resume = new Resume();
        resume.setState(ResumeState.NEW);
        resume.setData(data);
        resume.setCandidate(newCandidate);

        Resume newResume = resumeRepository.saveAndFlush(resume);
        LOG.info("New resume created with id: " + newResume.getId());
    }
}
