package com.spring.batch.springBatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.JobStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ParentJobConfiguration {
    private Job childJob;
    private JobLauncher jobLauncher;

    @Autowired
    public ParentJobConfiguration(Job childJob , JobLauncher jobLauncher){
      this.childJob = childJob;
      this.jobLauncher = jobLauncher;
    }

    @Bean
    public Step step13(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("step13", jobRepository).tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("step13");
                return RepeatStatus.FINISHED;
            }
        }, platformTransactionManager).build();
    }

//    @Bean
//    public Job parentJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
//        Step childJobStep = new JobStepBuilder(new StepBuilder("childJobStep", jobRepository))
//                .job(childJob).launcher(jobLauncher).build();
//
//        return new JobBuilder("parentJob", jobRepository)
//                .start(step13(jobRepository, platformTransactionManager))
//                .next(childJobStep).build();
//    }
}
