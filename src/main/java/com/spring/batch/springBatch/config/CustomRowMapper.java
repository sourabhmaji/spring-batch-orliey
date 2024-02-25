package com.spring.batch.springBatch.config;

import com.spring.batch.springBatch.pojo.Customer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CustomRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new Customer(resultSet.getLong("id"),
                 resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                resultSet.getDate("birthDate"));
    }
}
