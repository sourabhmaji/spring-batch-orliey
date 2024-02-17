package com.spring.batch.springBatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ChildJobConfiguration {

    @Bean
    public Step step12(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("step12", jobRepository)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("In step 12");
                        return RepeatStatus.FINISHED;
                    }
                },platformTransactionManager).build();
    }

//    @Bean
//    public Job childJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
//        return new JobBuilder("childJob", jobRepository)
//                .start(step12(jobRepository, platformTransactionManager))
//                .build();
//    }
}
