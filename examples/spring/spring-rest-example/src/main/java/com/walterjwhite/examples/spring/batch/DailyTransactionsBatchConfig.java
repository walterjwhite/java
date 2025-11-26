package com.walterjwhite.examples.spring.batch;

import com.walterjwhite.examples.spring.batch.task.daily.FetchTransactionalData;
import com.walterjwhite.examples.spring.batch.task.daily.GenerateSummaryReport;
import com.walterjwhite.examples.spring.batch.task.daily.ProcessClientData;
import com.walterjwhite.examples.spring.batch.task.daily.Validate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.EnableJdbcJobRepository;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Isolation;

@Configuration
@EnableScheduling
@EnableJdbcJobRepository(
        dataSourceRef = "batchDataSource",
        transactionManagerRef = "batchTransactionManager",
        tablePrefix = "BATCH_",
        maxVarCharLength = 1000,
        isolationLevelForCreate = Isolation.SERIALIZABLE)
@EnableBatchProcessing
@RequiredArgsConstructor
@Slf4j
public class DailyTransactionsBatchConfig {
    private final JobOperator jobOperator;
    private final JobRegistry jobRegistry;

    @Bean
    public Job job(final JobRepository jobRepository, final Step fetchTransactionalData, final Step processClientData, final Step validate, final Step generateReport) {
        return new JobBuilder("dailyTransactions", jobRepository)
                .start(fetchTransactionalData)
                .next(processClientData)
                .next(validate)
                .next(generateReport)
                .build();
    }

    @Bean
    public Step fetchTransactionalData(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("fetchTransactionalData", jobRepository)
                .tasklet(new FetchTransactionalData(), transactionManager)
                .build();
    }

    @Bean
    public Step processClientData(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("processClientData", jobRepository)
                .tasklet(new ProcessClientData(), transactionManager)
                .build();
    }

    @Bean
    public Step validate(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("validate", jobRepository)
                .tasklet(new Validate(), transactionManager)
                .build();
    }

    @Bean
    public Step generateReport(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("generateReport", jobRepository)
                .tasklet(new GenerateSummaryReport(), transactionManager)
                .build();
    }

    @Scheduled(cron = "0 0 2 * * ?")
    public void launchFetchJob() {
        final String jobName = "dailyTransactions";
        final Job job = jobRegistry.getJob(jobName);
        try {
            LOGGER.info("Scheduled trigger: starting job {}", jobName);
            JobExecution jobExecution = jobOperator.start(job, new JobParameters());
            LOGGER.info("Job '{}' started with executionId={}", jobName, jobExecution);
        } catch (Exception e) {
            LOGGER.error("Failed to start job " + jobName, e);
        }
    }
}