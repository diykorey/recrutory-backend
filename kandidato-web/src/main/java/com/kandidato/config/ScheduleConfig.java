package com.kandidato.config;

import com.kandidato.schedule.ResumeIndexingJob;
import com.kandidato.util.AutowiringSpringBeanJobFactory;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;

@Configuration
public class ScheduleConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private DataSource quartzDataSource;

    @Bean
    public SchedulerFactoryBean newSchedulerFactory() throws Exception {
        JobDetail job = newJobDetail();

        SchedulerFactoryBean schedulerFactory = newSchedulerFactory(job, newTrigger(job));
        schedulerFactory.setJobFactory(jobFactory());

        return schedulerFactory;
    }

    private JobDetail newJobDetail(){
        return JobBuilder.newJob(ResumeIndexingJob.class).storeDurably().build();
    }

    private Trigger newTrigger(JobDetail job){
        return TriggerBuilder.newTrigger() //
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(2)
                .repeatForever()).forJob(job).startNow()//
                .build();//
    }

    private AutowiringSpringBeanJobFactory jobFactory(){
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);

        return jobFactory;
    }

    private SchedulerFactoryBean newSchedulerFactory(JobDetail job, Trigger trigger){
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setTriggers(trigger);
        schedulerFactory.setJobDetails(job);
        schedulerFactory.setDataSource(quartzDataSource);

        return schedulerFactory;
    }
}
