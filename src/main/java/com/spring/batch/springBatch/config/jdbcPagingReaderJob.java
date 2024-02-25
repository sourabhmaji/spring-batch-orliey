package com.spring.batch.springBatch.config;

import com.spring.batch.springBatch.pojo.Customer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;

import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class jdbcPagingReaderJob {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomRowMapper customRowMapper;

    @Bean
    public JdbcPagingItemReader<Customer> customerJdbcPagingItemReader(){
        JdbcPagingItemReader<Customer> jdbcPagingItemReader = new JdbcPagingItemReader<>();
        jdbcPagingItemReader.setDataSource(dataSource);
        jdbcPagingItemReader.setPageSize(5);
        jdbcPagingItemReader.setRowMapper(customRowMapper);

        MySqlPagingQueryProvider sql = new MySqlPagingQueryProvider();
        sql.setSelectClause("id,firstName,lastName,birthDate");
        sql.setFromClause("from customer");
        Map<String, Order> map = new HashMap<>();
        map.put("id", org.springframework.batch.item.database.Order.ASCENDING);
        sql.setSortKeys(map);

        jdbcPagingItemReader.setQueryProvider(sql);
        return jdbcPagingItemReader;
    }


    private ItemWriter<Customer> customerItemWriter(){
        return items-> {
            for(Customer customerItem : items){
                System.out.println(customerItem.toString());
            }
        };
    }

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("step1", jobRepository)
                .<Customer,Customer>chunk(5, transactionManager)
                .reader(customerJdbcPagingItemReader())
                .writer(customerItemWriter())
                .build();
    }

    @Bean
    public Job job(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new JobBuilder("job4", jobRepository)
                .start(step(jobRepository, platformTransactionManager))
                .build();
    }
}
