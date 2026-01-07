package com.walterjwhite.examples.spring.batch.dto;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Data;
import org.springframework.batch.core.job.JobExecution;

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

    if (jobExecution.getJobInstance() != null) {
      jobExecutionDto.jobName = jobExecution.getJobInstance().getJobName();
    } else {
      jobExecutionDto.jobName = null;
    }

    if (jobExecution.getStatus() != null) {
      jobExecutionDto.status = jobExecution.getStatus().toString();
    } else {
      jobExecutionDto.status = null;
    }

    if (jobExecution.getExitStatus() != null) {
      jobExecutionDto.exitStatus = jobExecution.getExitStatus().getExitCode();
    } else {
      jobExecutionDto.exitStatus = null;
    }

    jobExecutionDto.createTime = jobExecution.getCreateTime();
    jobExecutionDto.startTime = jobExecution.getStartTime();
    jobExecutionDto.endTime = jobExecution.getEndTime();

    jobExecutionDto.parameters = new LinkedHashMap<>();
    jobExecution
        .getJobParameters()
        .parameters()
        .forEach((p) -> jobExecutionDto.parameters.put(p.name(), p.value().toString()));

    return jobExecutionDto;
  }
}
