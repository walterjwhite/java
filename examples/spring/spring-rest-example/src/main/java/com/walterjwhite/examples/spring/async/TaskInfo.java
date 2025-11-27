package com.walterjwhite.examples.spring.async;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@Data
@EqualsAndHashCode
public class TaskInfo {
    public enum Status { QUEUED, RUNNING, COMPLETED, CANCELLED, FAILED }


    private final String id;
    @EqualsAndHashCode.Exclude
    private final String name;
    @EqualsAndHashCode.Exclude
    private final int durationSeconds;
    @EqualsAndHashCode.Exclude
    private final Instant submittedAt;

    @EqualsAndHashCode.Exclude
    private volatile Status status;
    @EqualsAndHashCode.Exclude
    private volatile Instant startedAt;
    @EqualsAndHashCode.Exclude
    private volatile Instant completedAt;
    @EqualsAndHashCode.Exclude
    private volatile String failureReason;

    public TaskInfo(String id, String name, int durationSeconds) {
        this.id = id;
        this.name = name;
        this.durationSeconds = durationSeconds;
        this.submittedAt = Instant.now();
        this.status = Status.QUEUED;
    }
}