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
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
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

    final JobParameters jobParameters =
        new JobParametersBuilder()
            .addString("from", from, true)
            .addString("subject", form.getSubject(), true)
            .addString("name", form.getName(), true)
            .addString("email", form.getEmail(), true)
            .addString("message", form.getMessage(), true)
            .toJobParameters();

    final Job contactEmailJob = jobRegistry.getJob("contactEmailJob");
    if (contactEmailJob == null) {
      throw new IllegalStateException("contactEmailJob not found");
    }

    jobOperator.start(contactEmailJob, jobParameters);
  }
}
