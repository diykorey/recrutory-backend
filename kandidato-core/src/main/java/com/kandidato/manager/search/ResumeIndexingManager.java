package com.kandidato.manager.search;


import com.kandidato.constants.ResumeState;
import com.kandidato.persistence.entity.Person;
import com.kandidato.persistence.entity.Resume;
import com.kandidato.persistence.repository.resume.ResumeRepository;
import com.kandidato.persistence.search.ResumeSearchRepository;
import com.kandidato.persistence.search.elastic.ResumeDocument;
import com.kandidato.util.TikaTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ResumeIndexingManager {

    private static final Logger LOG = LoggerFactory.getLogger(ResumeIndexingManager.class);

    @Autowired
    private ResumeSearchRepository resumeSearchRepository;

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private TikaTransformer transformer;

    public void indexResumes() {

        Collection<Resume> resumes = resumeRepository.findAll();

        for (Resume resume : resumes) {
            if(ResumeState.NEW.equals(resume.getState())){
                addToIndex(resume);
                LOG.info("Indexed resume with id: " + resume.getId());
                resume.setState(ResumeState.INDEXED);
                resumeRepository.saveAndFlush(resume);
            }
        }
    }

    private void addToIndex(Resume resume) {
        String text = transformer.textFrom(resume.getData());
        Person resumeOwner = resume.getResumeOwner();

        ResumeDocument doc = new ResumeDocument(resume.getId(), resumeOwner.getId(), text);

        LOG.info("Class: " + resumeSearchRepository.getClass());
        LOG.info("ResumeDocument: " + doc);

        resumeSearchRepository.index(doc);
    }
}
