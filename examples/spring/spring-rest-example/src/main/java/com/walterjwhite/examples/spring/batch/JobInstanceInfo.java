package com.walterjwhite.examples.spring.batch;

import lombok.Data;

import java.util.List;

@Data
public class JobInstanceInfo {
    private final long instanceId;
    private final String jobName;
    private final List<JobExecutionInfo> executions;
}