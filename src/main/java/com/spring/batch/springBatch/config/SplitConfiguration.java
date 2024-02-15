package com.spring.batch.springBatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SplitConfiguration {

    @Bean
    public Tasklet tasklet(){
        return (contribution, chunkContext) -> {
            System.out.println("Tasklet is running on thread "+ Thread.currentThread().getName());
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Step step9(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("step9", jobRepository).tasklet(tasklet(),platformTransactionManager).build();
    }

    @Bean
    public Step step10(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("step10", jobRepository).tasklet(tasklet(),platformTransactionManager).build();
    }

    @Bean
    public Step step11(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("step11", jobRepository).tasklet(tasklet(),platformTransactionManager).build();
    }

    @Bean
    public Flow flow2(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flow2");
        flowBuilder.start(step9(jobRepository, platformTransactionManager)).end();
        return flowBuilder.build();
    }

    @Bean
    public Flow flow3(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flow3");
        flowBuilder.start(step10(jobRepository,platformTransactionManager))
                .next(step11(jobRepository, platformTransactionManager))
                .end();
        return flowBuilder.build();
     }

/*     @Bean
     public Job job5(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new JobBuilder("job5", jobRepository).start(flow3(jobRepository, platformTransactionManager))
                .split(new SimpleAsyncTaskExecutor()).add(flow2(jobRepository, platformTransactionManager)).end().build();
     }*/
}
