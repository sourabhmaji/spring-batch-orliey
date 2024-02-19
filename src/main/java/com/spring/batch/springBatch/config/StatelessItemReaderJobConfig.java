package com.spring.batch.springBatch.config;

import com.spring.batch.springBatch.config.reader.StatelessItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class StatelessItemReaderJobConfig {

    @Bean
    public StatelessItemReader statelessItemReader(){
        List<String> list = new ArrayList<>();

        list.add("Profit");
        list.add("Money");
        list.add("Influence");
        return new StatelessItemReader(list);
    }

/*    public ItemWriter<String> itemWriter(){
        return new ListItemWriter<String>(){
            public void write(List<? extends String> list) throws Exception {
              for(String item : list)
                  System.out.println("Item is "+ item);
            }
        };
    }*/

    @Bean
    public Step step15(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("step15", jobRepository)
                .<String,String>chunk(1, transactionManager)
                .reader(statelessItemReader())
                .writer((list)->{
                    for(String lst : list) {
                        System.out.println(lst);
                    }
                })
                .build();
    }

/*    @Bean
    public Job job1(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new JobBuilder("job1", jobRepository).start(step15(jobRepository, transactionManager)).build();
    }*/
}
