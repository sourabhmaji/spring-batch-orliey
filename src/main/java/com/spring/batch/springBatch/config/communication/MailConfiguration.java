package com.spring.batch.springBatch.config.communication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfiguration {

//    @Bean
//    public JavaMailSender mail(){
//        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
//      javaMailSender.setHost("smtp.gmail.com");
//      javaMailSender.setPort(587);
//
//      javaMailSender.setUsername("sourabhkumarmaji2018@gmail.com");
//      javaMailSender.setPassword("#Skm@131189");
//
//      Properties props = javaMailSender.getJavaMailProperties();
//      props.put("mail.transport.protocol", "smtp");
//      props.put("mail.smtp.auth", "true");
//      props.put("mail.smtp.starttls.enable", "true");
//      props.put("mail.debug", "true");
//
//      return javaMailSender;
//    }
}
