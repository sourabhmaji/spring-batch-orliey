package com.spring.batch.springBatch.config;

import org.springframework.batch.core.*;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JobDecider {
    public static class OddDecider implements JobExecutionDecider{
       private int count = 0;
        @Override
        public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
            count++;
            if(count % 2 ==0 )
                return new FlowExecutionStatus("EVEN");
            else
                return new FlowExecutionStatus("ODD");
        }
    }

    @Bean
    public Step evenStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("evenStep", jobRepository).tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("Inside evenStep");
                return RepeatStatus.FINISHED;
            }
        }, platformTransactionManager).build();
    }

    @Bean
    public Step oddStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("oddStep", jobRepository).tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("Inside oddStep");
                return RepeatStatus.FINISHED;
            }
        }, platformTransactionManager).build();
    }

    public Step startStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("startStep", jobRepository).tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("Inside startStep");
                return RepeatStatus.FINISHED;
            }
        },platformTransactionManager).build();
    }

    @Bean
    public JobExecutionDecider decider(){
        return new OddDecider();
    }

    @Bean
    public Job job(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new JobBuilder("job9", jobRepository)
                .start(startStep(jobRepository,platformTransactionManager))
                .next(decider())
                .from(decider()).on("ODD").to(oddStep(jobRepository, platformTransactionManager))
                .from(decider()).on("EVEN").to(evenStep(jobRepository, platformTransactionManager))
                .from(oddStep(jobRepository, platformTransactionManager)).on("*").to(decider())
             /*   .from(decider()).on("ODD").to(oddStep(jobRepository, platformTransactionManager))
                .from(decider()).on("EVEN").to(evenStep(jobRepository, platformTransactionManager)) not needed because looping*/
                .end().build();
    }
}
