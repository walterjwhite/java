package com.walterjwhite.examples.spring.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.configuration.JobRegistry;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.job.JobInstance;
import org.springframework.batch.core.launch.JobOperator;

import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BatchJobService {
    private final JobRepository jobRepository;
    private final JobOperator jobOperator;
    private final JobRegistry jobRegistry;

    public JobExecution startJob(final String jobName, final Map<String, String> params) throws Exception {
        if (!isJobRegistered(jobName)) {
            throw new NoSuchJobException("Job not registered: " + jobName);
        }

        Map<String, String> effective = new LinkedHashMap<>();
        if (params != null) effective.putAll(params);
        effective.putIfAbsent("ts", String.valueOf(System.currentTimeMillis()));

        String paramString = effective.entrySet().stream()
                .map(e -> e.getKey() + "=" + escapeParameterValue(e.getValue()))
                .collect(Collectors.joining(","));

        final Job job = jobRegistry.getJob(jobName);
        return jobOperator.start(job, JobParametersUtil.toJobParameters(params, false));
    }

    public boolean stopExecution(final long jobExecutionId) throws Exception {
        final JobExecution jobExecution = jobRepository.getJobExecution(jobExecutionId);
        if(jobExecution == null) {
            throw new IllegalArgumentException("Job Execution ID: " + jobExecutionId + " not found.");
        }

        if(jobExecution.isRunning()) {
            jobExecution.setStatus(BatchStatus.STOPPING);
            jobRepository.update(jobExecution);

            LOGGER.warn("stopping job: {}", jobExecution);

            return true;
        }

        LOGGER.warn("job: {} is not presently running", jobExecution);
        return false;
    }

    public boolean abandonExecution(long jobExecutionId) throws Exception {
        final JobExecution jobExecution = jobRepository.getJobExecution(jobExecutionId);
        if(jobExecution == null) {
            throw new IllegalArgumentException("Job Execution ID: " + jobExecutionId + " not found.");
        }

        if(jobExecution.isRunning()) {
            jobExecution.setStatus(BatchStatus.ABANDONED);
            jobRepository.update(jobExecution);

            LOGGER.warn("abandoned job: {}", jobExecution);

            return true;
        }

        LOGGER.warn("job: {} is not presently running", jobExecution);
        return false;
    }

    public boolean isJobRegistered(String jobName) {
        return jobRegistry.getJobNames().contains(jobName);
    }

    public JobExecution getJobExecution(long jobExecutionId) {
        return jobRepository.getJobExecution(jobExecutionId);
    }

    public List<JobInstanceInfo> getJobHistory(String jobName, int start, int count) {
        final List<JobInstance> jobInstances = jobRepository.getJobInstances(jobName, start, count);
        List<JobInstanceInfo> result = new ArrayList<>(jobInstances.size());

        for (JobInstance instance : jobInstances) {
            final List<JobExecution> executions = jobRepository.getJobExecutions(instance);
            final List<JobExecutionInfo> execInfos = executions.stream()
                    .map(JobExecutionInfo::from)
                    .sorted(Comparator.comparing(JobExecutionInfo::getStartTime).reversed())
                    .collect(Collectors.toList());
            result.add(new JobInstanceInfo(instance.getInstanceId(), instance.getJobName(), execInfos));
        }

        return result;
    }

    /*
     * Small helpers & DTOs
     */
    private static String escapeParameterValue(String v) {
        return v.replace("\\", "\\\\").replace(",", "\\,");
    }
}