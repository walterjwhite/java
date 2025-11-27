package com.walterjwhite.examples.spring.batch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.support.JobOperatorFactoryBean;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JdbcJobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@Slf4j
public class JobOperatorConfig {

    @Primary
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setName("batchdb")
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public JobRegistry jobRegistry(final ApplicationContext applicationContext) {
        final MapJobRegistry mapJobRegistry = new MapJobRegistry();
        mapJobRegistry.setApplicationContext(applicationContext);
        mapJobRegistry.afterSingletonsInstantiated();
        return mapJobRegistry;
    }

    @Bean(name = "taskExecutor")
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor exec = new ThreadPoolTaskExecutor();
        exec.setCorePoolSize(5);
        exec.setMaxPoolSize(10);
        exec.setQueueCapacity(50);
        exec.setThreadNamePrefix("batch-");
        exec.initialize();
        return exec;
    }


    @Primary
    @Bean(name = "customJobOperator")
    public JobOperator jobOperator(
            JobRepository jobRepository,
            TaskExecutor taskExecutor, ApplicationContext applicationContext) throws Exception {

        LOGGER.warn("creating jobOperator with: {}", taskExecutor);
        JobOperatorFactoryBean factory = new JobOperatorFactoryBean();
        factory.setApplicationContext(applicationContext);
        factory.setJobRepository(jobRepository);

        factory.setTaskExecutor(taskExecutor);
        factory.afterPropertiesSet();
        return factory.getObject();
    }
}