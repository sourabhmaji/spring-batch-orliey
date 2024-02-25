package com.spring.batch.springBatch.config;

import com.spring.batch.springBatch.pojo.Customer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class jdbcCursorJob {
//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    private RowMapper<Customer> customRowMapper;
//
//    @Bean
//    public JdbcCursorItemReader<Customer> customerItemReader(){
//       JdbcCursorItemReader<Customer> reader = new JdbcCursorItemReader<>();
//       reader.setSql("SELECT id, firstName, lastName, birthDate FROM customer ORDER BY id");
//       reader.setDataSource(dataSource);
//       reader.setRowMapper(customRowMapper);
//       return reader;
//    }
//
//    private ItemWriter<Customer> customerItemWriter(){
//        return items-> {
//            for(Customer customerItem : items){
//                System.out.println(customerItem.toString());
//            }
//        };
//    }
//
//    @Bean
//    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager){
//        return new StepBuilder("step1", jobRepository)
//                .<Customer,Customer>chunk(5, transactionManager)
//                .reader(customerItemReader())
//                .writer(customerItemWriter())
//                .build();
//    }
//  @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    private RowMapper<Customer> customRowMapper;
//
//    @Bean
//    public JdbcCursorItemReader<Customer> customerItemReader(){
//       JdbcCursorItemReader<Customer> reader = new JdbcCursorItemReader<>();
//       reader.setSql("SELECT id, firstName, lastName, birthDate FROM customer ORDER BY id");
//       reader.setDataSource(dataSource);
//       reader.setRowMapper(customRowMapper);
//       return reader;
//    }
//
//    private ItemWriter<Customer> customerItemWriter(){
//        return items-> {
//            for(Customer customerItem : items){
//                System.out.println(customerItem.toString());
//            }
//        };
//    }
//
//    @Bean
//    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager){
//        return new StepBuilder("step1", jobRepository)
//                .<Customer,Customer>chunk(5, transactionManager)
//                .reader(customerItemReader())
//                .writer(customerItemWriter())
//                .build();
//    }

/*    @Bean
    public Job job(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new JobBuilder("job2", jobRepository).start(step(jobRepository,transactionManager)).build();
    }*/

}
