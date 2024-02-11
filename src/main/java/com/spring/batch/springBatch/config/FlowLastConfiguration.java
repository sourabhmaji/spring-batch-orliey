package com.spring.batch.springBatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
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
public class FlowLastConfiguration {

    @Bean
    public Step step8(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("step8",jobRepository).tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("Step8 execution in FlowLastConfiguration");
                return RepeatStatus.FINISHED;
            }
        },transactionManager).build();
    }

//    @Bean
//    public Job job4(JobRepository jobRepository, PlatformTransactionManager transactionManager, Flow flow){
//        return new JobBuilder("job4", jobRepository)
//                .start(step8(jobRepository,transactionManager))
//                .on("COMPLETED").to(flow).end().build();
//    }
}
