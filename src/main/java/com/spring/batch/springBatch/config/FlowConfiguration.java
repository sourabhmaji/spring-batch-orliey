package com.spring.batch.springBatch.config;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class FlowConfiguration {

    @Bean
    public Step step5(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("step5",jobRepository).tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("Inside FlowConfiguration flowBuilder1 step5");
                return RepeatStatus.FINISHED;
            }
        },transactionManager).build();
    }

    @Bean
    public Step step6(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("step6", jobRepository).tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("Inside FlowConfiguration flowBuilder1 step6");
                return RepeatStatus.FINISHED;
            }
        },platformTransactionManager).build();
    }

    @Bean
    public Flow flow(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flowBuilder1");

        flowBuilder.start(step5(jobRepository,platformTransactionManager))
                .next(step6(jobRepository, platformTransactionManager))
                .end();

        return flowBuilder.build();
    }
}
