package com.walterjwhite.examples.spring.contact;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.parameters.InvalidJobParametersException;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.launch.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.JobRestartException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "app.contact", name = "enabled", havingValue = "true")
public class ContactService {
  private final JobOperator jobOperator;
  private final JobRegistry jobRegistry;

  @Value("${spring.mail.username}")
  private String from;

  public void send(final ContactForm form)
      throws JobInstanceAlreadyCompleteException,
          InvalidJobParametersException,
          JobExecutionAlreadyRunningException,
          JobRestartException {
    form.validate();

    JobParameters params =
        new JobParametersBuilder()
            .addString("from", from)
            .addString("subject", form.getSubject())
            .addString("name", form.getName())
            .addString("email", form.getEmail())
            .addString("message", form.getMessage())
            .addLong("ts", System.currentTimeMillis())
            .toJobParameters();

    final Job contactEmailJob = jobRegistry.getJob("contactEmailJob");
    jobOperator.start(contactEmailJob, params);
  }
}
