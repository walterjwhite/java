 package com.walterjwhite.examples.spring.contact;

 import lombok.RequiredArgsConstructor;
 import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
 import org.springframework.batch.core.job.Job;
 import org.springframework.batch.core.job.builder.JobBuilder;
 import org.springframework.batch.core.repository.JobRepository;
 import org.springframework.batch.core.step.Step;
 import org.springframework.batch.core.step.builder.StepBuilder;
 import org.springframework.beans.factory.annotation.Qualifier;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;

 @Configuration
 @EnableBatchProcessing
 @RequiredArgsConstructor
 public class ContactEmailBatchConfig {
  private final JobRepository jobRepository;
  private final EmailTasklet emailTasklet;

  @Bean("contactEmailJob")
  public Job contactEmailJob(@Qualifier("contactEmailStep") final Step contactEmailStep) {
    return new JobBuilder("contactEmailJob", jobRepository).start(contactEmailStep).build();
  }

  @Bean("contactEmailStep")
  public Step contactEmailStep() {
    return new StepBuilder("contactEmailStep", jobRepository).tasklet(emailTasklet).build();
  }
 }
