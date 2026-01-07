package com.walterjwhite.examples.spring.contact;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ContactEmailBatchConfig {
  @Bean("contactEmailJob")
  public Job contactEmailJob(
      final JobRepository jobRepository, @Qualifier("contactEmailStep") final Step step) {
    return new JobBuilder(jobRepository).start(step).build();
  }

  @Bean("contactEmailStep")
  public Step contactEmailStep(final JobRepository jobRepository, final EmailTasklet emailTasklet) {
    return new StepBuilder("contactEmailStep", jobRepository).tasklet(emailTasklet).build();
  }
}
