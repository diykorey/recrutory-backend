package com.kandidato.manager.search;

import com.kandidato.manager.candidate.CandidateManager;
import com.kandidato.model.dto.BaseCandidateDTO;
import com.kandidato.model.dto.ResumeDTO;
import com.kandidato.model.dto.input.CandidateIn;
import com.kandidato.persistence.entity.Resume;
import com.kandidato.persistence.repository.resume.ResumeRepository;
import com.kandidato.security.model.CurrentProfile;
import com.kandidato.util.DozerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ResumeManagerImpl implements ResumeManager {

    private static final Logger LOG = LoggerFactory.getLogger(ResumeManagerImpl.class);

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private CandidateManager candidateManager;

    @Autowired
    private DozerUtil mapper;

    @Autowired
    private BlobManager blobManager;

    @Override
    public BaseCandidateDTO createResume(CurrentProfile currentUser, ResumeDTO resume) {
        BaseCandidateDTO candidate = saveCandidate(currentUser, resume);
        resume.setCandidate(candidate);
        saveResume(currentUser, resume);
        return candidate;
    }

    @Override
    public BaseCandidateDTO createResume(CurrentProfile currentUser, long candidateId, ResumeDTO resume) {
        BaseCandidateDTO candidate = candidateManager.getCandidate(candidateId);
        resume.setCandidate(candidate);
        saveResume(currentUser, resume);
        return candidate;
    }

    private BaseCandidateDTO saveCandidate(CurrentProfile currentUser, ResumeDTO resume) {
        LOG.info("Creating candidate");
        BaseCandidateDTO newCandidate = candidateManager.create(currentUser, new CandidateIn());
        LOG.info("New candidate created with id: {}", newCandidate.getId());
        return newCandidate;
    }

    private void saveResume(CurrentProfile currentUser, ResumeDTO resumeDto) {
        Resume resume = mapper.map(resumeDto, Resume.class);
        try {
            String resumeBlobUrl = blobManager.upload(
                    newBlobName(currentUser),
                    resumeDto.getData(),
                    resumeDto.getContentType());
            resume.setDataUrl(resumeBlobUrl);
            Resume newResume = resumeRepository.saveAndFlush(resume);
            LOG.info("New resume created with id: {}", newResume.getId());
        } catch (Exception e) {
            LOG.error("Can't save resume.", e);
        }
    }

    private String newBlobName(CurrentProfile currentUser) {
        Long id = currentUser.getId();
        return id + "/" + UUID.randomUUID().toString() + ".blob";
    }
}