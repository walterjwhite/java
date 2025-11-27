package com.walterjwhite.examples.spring.batch;

import com.walterjwhite.examples.spring.batch.dto.JobExecutionInfo;
import com.walterjwhite.examples.spring.batch.dto.JobInstanceInfo;
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
        final Job job = jobRegistry.getJob(jobName);
        if(job == null) {
            throw new NoSuchJobException("Job not registered: " + jobName);
        }

        return jobOperator.start(job, JobParametersUtil.toJobParameters(params, false));
    }

    public boolean stopExecution(final long jobExecutionId) {
        return stop(jobExecutionId, BatchStatus.STOPPING);
    }

    public boolean abandonExecution(long jobExecutionId) {
        return stop(jobExecutionId, BatchStatus.ABANDONED);
    }

    protected boolean stop(final long jobExecutionId, final BatchStatus batchStatus) {
        final JobExecution jobExecution = jobRepository.getJobExecution(jobExecutionId);
        if(jobExecution == null) {
            throw new IllegalArgumentException("Job Execution ID: " + jobExecutionId + " not found.");
        }

        if(jobExecution.isRunning()) {
            jobExecution.setStatus(batchStatus);
            jobRepository.update(jobExecution);

            LOGGER.warn("{} job: {}", batchStatus.name(), jobExecution);

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
}