package com.walterjwhite.examples.spring.batch.dto;

import lombok.Data;
import org.springframework.batch.core.job.JobExecution;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class JobExecutionInfo {
    private final long executionId;
    private final long instanceId;
    private final String status;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final String exitCode;
    private final String exitDescription;
    private final Map<String, Object> parameters;

    public static JobExecutionInfo from(JobExecution jobExecution) {
        Map<String, Object> params = new LinkedHashMap<>();
        jobExecution.getJobParameters().parameters().forEach((p) -> params.put(p.name(), p.value()));
        return new JobExecutionInfo(
                jobExecution.getId(),
                jobExecution.getJobInstance().getInstanceId(),
                jobExecution.getStatus().toString(),
                jobExecution.getStartTime(),
                jobExecution.getEndTime(),
                jobExecution.getExitStatus() != null ? jobExecution.getExitStatus().getExitCode() : null,
                jobExecution.getExitStatus() != null ? jobExecution.getExitStatus().getExitDescription() : null,
                params
        );
    }
}
