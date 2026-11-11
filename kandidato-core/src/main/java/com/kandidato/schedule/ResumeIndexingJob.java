package com.kandidato.schedule;

import com.kandidato.manager.search.ResumeIndexingManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ResumeIndexingJob {

    private static final Logger LOG = LoggerFactory.getLogger(ResumeIndexingJob.class);

    @Autowired
    private ResumeIndexingManager resumeIndexingManager;

    @Scheduled(fixedDelay = 5000)
    public void execute() {
        LOG.trace("ResumeIndexingJob is executed.");
        resumeIndexingManager.indexResumes();
    }
}
