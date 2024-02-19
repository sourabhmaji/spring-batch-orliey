package com.spring.batch.springBatch.config.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@Configuration
public class JobListener implements JobExecutionListener {

    private JavaMailSender mailSender;

    public JobListener(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }
    @Override
    public void beforeJob(JobExecution jobExecution) {
        String jobName = jobExecution.getJobInstance().getJobName();

        SimpleMailMessage mail = getMailMessage(String.format("%s is starting", jobName),
                               String.format("per you request we wish to inform that job %s is starting", jobName));
        mailSender.send(mail);
    }

    @Override
    public void afterJob(JobExecution jobExecution){
        String jobName = jobExecution.getJobInstance().getJobName();
        SimpleMailMessage mailMessage = getMailMessage(String.format("%s is stopping", jobName),
                                     String.format("per your request we wish to inform you that job %s is stopping", jobName));
        mailSender.send(mailMessage);
    }

    private SimpleMailMessage getMailMessage(String subject, String text){
       SimpleMailMessage message = new SimpleMailMessage();
       message.setTo("jackhill12396@yahoo.com");
       message.setSubject(subject);
       message.setText(text);
       return message;
    }
}
