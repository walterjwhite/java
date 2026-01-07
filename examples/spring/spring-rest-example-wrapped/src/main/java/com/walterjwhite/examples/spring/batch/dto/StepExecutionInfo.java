package com.walterjwhite.examples.spring.batch.dto;

import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.batch.core.step.StepExecution;

@Data
public class StepExecutionInfo {
  private final long id;
  private final long executionId;
  private final String stepName;
  private final String status;
  private final LocalDateTime startTime;
  private final LocalDateTime endTime;
  private final long readCount;
  private final long writeCount;
  private final long commitCount;
  private final String exitCode;
  private final String exitDescription;

  public static StepExecutionInfo from(StepExecution se) {
    String statusStr;
    if (se.getStatus() != null) {
      statusStr = se.getStatus().toString();
    } else {
      statusStr = null;
    }

    String exitCode;
    if (se.getExitStatus() != null) {
      exitCode = se.getExitStatus().getExitCode();
    } else {
      exitCode = null;
    }

    String exitDesc;
    if (se.getExitStatus() != null) {
      exitDesc = se.getExitStatus().getExitDescription();
    } else {
      exitDesc = null;
    }

    return new StepExecutionInfo(
        se.getId(),
        se.getJobExecutionId(),
        se.getStepName(),
        statusStr,
        se.getStartTime(),
        se.getEndTime(),
        se.getReadCount(),
        se.getWriteCount(),
        se.getCommitCount(),
        exitCode,
        exitDesc);
  }
}
