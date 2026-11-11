package com.kandidato.manager.search;


import com.kandidato.persistence.entity.Candidate;
import com.kandidato.persistence.entity.Resume;
import com.kandidato.persistence.repository.resume.ResumeRepository;
import com.kandidato.persistence.search.repository.ResumeSearchRepository;
import com.kandidato.persistence.search.model.ResumeDocument;
import com.kandidato.util.TikaTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import static com.kandidato.constants.SearchIndexState.*;

@Component
public class ResumeIndexingManagerImpl implements ResumeIndexingManager {

    private static final Logger LOG = LoggerFactory.getLogger(ResumeIndexingManagerImpl.class);

    @Autowired
    private ResumeSearchRepository resumeSearchRepository;

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private BlobManager blobManager;

    @Autowired
    private TikaTransformer tikaTransformer;

    @Override
    public void indexResumes() {
        for (Resume resume : resumeRepository.findByStateIs(NEW, new PageRequest(0, 5))) {
            try {
                addToIndex(resume);
                resume.setState(INDEXED);
                LOG.info("Resume with ID {} is processed.", resume.getId());
            } catch (Exception e) {
                resume.setState(ERROR);
                LOG.error("Resume with ID {} indexing failed because of the following exception: {} ",
                        resume.getId(), e.getMessage());
            }
            resumeRepository.saveAndFlush(resume);
        }
    }

    private void addToIndex(Resume resume) throws Exception {
        byte[] blob = blobManager.download(resume.getDataUrl());
        String text = tikaTransformer.textFrom(blob);
        Candidate resumeOwner = resume.getCandidate();
        ResumeDocument doc = new ResumeDocument(resume, resumeOwner, text);
        resumeSearchRepository.index(doc);
        LOG.info("ResumeDocument with ID {} is indexed.", doc.getId());
    }
}
