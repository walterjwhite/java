package com.walterjwhite.examples.spring.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.launch.support.JobOperatorFactoryBean;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JdbcJobRepositoryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.*;

import javax.sql.DataSource;

@Configuration
@Slf4j
public class JobOperatorConfig {
    @Bean(name = "batchDataSource")
    public DataSource batchDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setName("batchdb") // jdbc:h2:mem:batchdb
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:org/springframework/batch/core/schema-h2.sql")
                .build();
    }

    @Bean
    public PlatformTransactionManager batchTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor exec = new ThreadPoolTaskExecutor();
        exec.setCorePoolSize(5);
        exec.setMaxPoolSize(10);
        exec.setQueueCapacity(50);
        exec.setThreadNamePrefix("batch-");
        exec.initialize();
        return exec;
    }

    @Bean
    public JobRepository jobRepository(final DataSource dataSource, final PlatformTransactionManager transactionManager) throws Exception {
        JdbcJobRepositoryFactoryBean factory = new JdbcJobRepositoryFactoryBean();
        factory.setDataSource(dataSource);
        factory.setDatabaseType("db2");
        factory.setTransactionManager(transactionManager);
        return factory.getObject();
    }

    @Bean
    public JobOperatorFactoryBean jobOperator(final JobRepository jobRepository, final TaskExecutor taskExecutor) {
        JobOperatorFactoryBean jobOperatorFactoryBean = new JobOperatorFactoryBean();
        jobOperatorFactoryBean.setJobRepository(jobRepository);

        LOGGER.warn("setting task executor: {}", taskExecutor);
        jobOperatorFactoryBean.setTaskExecutor(taskExecutor/*new SimpleAsyncTaskExecutor()*/);
        return jobOperatorFactoryBean;
    }

}