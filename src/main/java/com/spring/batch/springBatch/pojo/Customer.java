package com.spring.batch.springBatch.pojo;

import java.util.Date;

public class Customer {
    private final long id;
    private final String firstName;
    private final String lastName;
    private final Date birthDate;

    public Customer(long id, String firstName, String lastName, Date birthDate){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
