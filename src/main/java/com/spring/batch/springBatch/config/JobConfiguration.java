package com.spring.batch.springBatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JobConfiguration {

//    @Bean
//    public Job job(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
//        return new JobBuilder("job1",jobRepository)
//                .start(step3(jobRepository, platformTransactionManager))
//                .next(step4(jobRepository, platformTransactionManager))
//                .build();
//    }

    @Bean
    public Job job2(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
      return new JobBuilder("job2",jobRepository)
              .start(step3(jobRepository, platformTransactionManager)).on("COMPLETED")
              .to(step4(jobRepository, platformTransactionManager))
              .from(step4(jobRepository, platformTransactionManager)).end().build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
      return new StepBuilder("step1", jobRepository).
              chunk(2,platformTransactionManager)
              .reader(new JsonItemReader<>())
              .writer(new JdbcBatchItemWriter<>())
              .build();
    }

    @Bean
    public Step step2(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("step2", jobRepository)
                .chunk(2, platformTransactionManager)
                .reader(new JsonItemReader<>())
                .writer(new JdbcBatchItemWriter<>())
                .build();
    }

    @Bean
    public Step step3(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("step8", jobRepository).
                tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                       System.out.println("In Step8");
                        return RepeatStatus.FINISHED;
                    }
                }, platformTransactionManager).
                build();
    }

    @Bean
    public Step step4(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("step9", jobRepository).
                tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("In Step9");
                        return RepeatStatus.FINISHED;
                    }
                }, platformTransactionManager).
                build();
    }
}
