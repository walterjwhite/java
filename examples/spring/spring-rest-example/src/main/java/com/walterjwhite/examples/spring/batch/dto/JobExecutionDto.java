package com.walterjwhite.examples.spring.batch.dto;

import lombok.Data;
import org.springframework.batch.core.job.JobExecution;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 * DTO for exposing a JobExecution over the REST API.
 * Keeps a small, stable JSON shape rather than exposing the full framework type.
 */
@Data
public class JobExecutionDto {
    public long executionId;
    public String jobName;
    public String status;
    public String exitStatus;
    public LocalDateTime createTime;
    public LocalDateTime startTime;
    public LocalDateTime endTime;
    public Map<String, String> parameters;

    public static JobExecutionDto from(final JobExecution jobExecution) {
        JobExecutionDto jobExecutionDto = new JobExecutionDto();
        if (jobExecution == null) {
            return jobExecutionDto;
        }

        jobExecutionDto.executionId = jobExecution.getId();
        jobExecutionDto.jobName = jobExecution.getJobInstance() != null ? jobExecution.getJobInstance().getJobName() : null;
        jobExecutionDto.status = jobExecution.getStatus() != null ? jobExecution.getStatus().toString() : null;
        jobExecutionDto.exitStatus = jobExecution.getExitStatus() != null ? jobExecution.getExitStatus().getExitCode() : null;
        jobExecutionDto.createTime = jobExecution.getCreateTime();
        jobExecutionDto.startTime = jobExecution.getStartTime();
        jobExecutionDto.endTime = jobExecution.getEndTime();

        jobExecutionDto.parameters = new LinkedHashMap<>();
        jobExecution.getJobParameters().parameters().forEach((p) -> jobExecutionDto.parameters.put(p.name(), p.value().toString()));

        return jobExecutionDto;
    }
}