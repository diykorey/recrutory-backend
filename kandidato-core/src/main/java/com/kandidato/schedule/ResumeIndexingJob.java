package com.kandidato.schedule;

import com.kandidato.manager.search.ResumeIndexingManager;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class ResumeIndexingJob extends QuartzJobBean {

    private static final Logger LOG = LoggerFactory.getLogger(ResumeIndexingJob.class);

    @Autowired
    private ResumeIndexingManager resumeIndexingManager;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        resumeIndexingManager.indexResumes();
    }
}
