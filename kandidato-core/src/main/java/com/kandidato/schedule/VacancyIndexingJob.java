package com.kandidato.schedule;

import com.kandidato.manager.search.vacancy.VacancyIndexer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class VacancyIndexingJob {

    private static final Logger LOG = LoggerFactory.getLogger(VacancyIndexingJob.class);

    @Autowired
    private VacancyIndexer vacancyIndexer;

    @Scheduled(fixedDelay = 5000)
    public void execute() {
        LOG.trace("Starting vacancy indexing job.");
        vacancyIndexer.indexVacancies();
        LOG.trace("Vacancy indexing job completed.");
    }
}

