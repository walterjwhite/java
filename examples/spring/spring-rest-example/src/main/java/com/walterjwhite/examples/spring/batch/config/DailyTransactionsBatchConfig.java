package com.walterjwhite.examples.spring.batch.config;

import com.walterjwhite.examples.spring.batch.task.daily.FetchTransactionalData;
import com.walterjwhite.examples.spring.batch.task.daily.GenerateSummaryReport;
import com.walterjwhite.examples.spring.batch.task.daily.ProcessClientData;
import com.walterjwhite.examples.spring.batch.task.daily.Validate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableJdbcJobRepository;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Isolation;

@Configuration
@EnableScheduling
@EnableJdbcJobRepository(maxVarCharLength = 1000, isolationLevelForCreate = Isolation.SERIALIZABLE)
@RequiredArgsConstructor
@Slf4j
public class DailyTransactionsBatchConfig {
  public static final String DAILY_TRANSACTIONS_JOB_NAME = "dailyTransactions";

  private final JobOperator jobOperator;
  private final JobRegistry jobRegistry;

  @Bean("dailyTransactionsJob")
  public Job job(
      final JobRepository jobRepository,
      @Qualifier("fetchTransactionalData") final Step fetchTransactionalData,
      @Qualifier("processClientData") final Step processClientData,
      @Qualifier("validate") final Step validate,
      @Qualifier("generateReport") final Step generateReport) {
    return new JobBuilder("dailyTransactions", jobRepository)
        .start(fetchTransactionalData)
        .next(processClientData)
        .next(validate)
        .next(generateReport)
        .build();
  }

  @Bean("fetchTransactionalData")
  public Step fetchTransactionalData(
      JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return new StepBuilder("fetchTransactionalData", jobRepository)
        .tasklet(new FetchTransactionalData(), transactionManager)
        .build();
  }

  @Bean("processClientData")
  public Step processClientData(
      JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return new StepBuilder("processClientData", jobRepository)
        .tasklet(new ProcessClientData(), transactionManager)
        .build();
  }

  @Bean("validate")
  public Step validate(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return new StepBuilder("validate", jobRepository)
        .tasklet(new Validate(), transactionManager)
        .build();
  }

  @Bean("generateReport")
  public Step generateReport(
      JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return new StepBuilder("generateReport", jobRepository)
        .tasklet(new GenerateSummaryReport(), transactionManager)
        .build();
  }

  @Scheduled(cron = "0 0 2 * * ?")
  public void scheduledRun() {
    final Job job = jobRegistry.getJob(DAILY_TRANSACTIONS_JOB_NAME);
    try {
      LOGGER.info("Scheduled trigger: starting job {}", DAILY_TRANSACTIONS_JOB_NAME);
      JobExecution jobExecution = jobOperator.start(job, new JobParameters());
      LOGGER.info(
          "Job '{}' started with executionId={}", DAILY_TRANSACTIONS_JOB_NAME, jobExecution);
    } catch (Exception e) {
      LOGGER.error("Failed to start job " + DAILY_TRANSACTIONS_JOB_NAME, e);
    }
  }
}
