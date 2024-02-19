package com.spring.batch.springBatch.config.listener;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.item.support.ListItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ListenerJobConfiguration {

    @Bean
    public ItemReader<String> reader(){
        return new ListItemReader<>(Arrays.asList("one", "two","three"));
    }

    @Bean
    public ItemWriter<String> writer(){
        return new ListItemWriter<String>(){
          public void write(List<? extends String> items) throws Exception{
            for(String item : items){
                System.out.println("Writing item "+item);
            }
          }
        };
    }

    @Bean
    public Step step14(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("Step14", jobRepository)
                .<String, String>chunk(2, platformTransactionManager)
                .faultTolerant()
                .listener(new ChunkListener())
                .reader(reader())
                .writer(writer())
                .build();
    }

/*    @Bean
    public Job job20(JavaMailSender javaMailSender, JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new JobBuilder("job20", jobRepository)
                .start(step14(jobRepository, platformTransactionManager))
                .listener(new JobListener(javaMailSender))
                .build();
    }*/
}
