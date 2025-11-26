package com.walterjwhite.examples.spring.batch_simple;

import lombok.Data;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
public class BatchJob {

    public enum Status { PENDING, QUEUED, RUNNING, COMPLETED, FAILED, CANCELLED }

    private final String id;
    private final String name;
    private final int durationSeconds; // simulated execution time
    private final Set<String> dependencies;
    private final Set<String> dependents = new HashSet<>();

    private volatile Status status = Status.PENDING;
    private volatile boolean wantToRun = false;
    private volatile Instant submittedAt;
    private volatile Instant startedAt;
    private volatile Instant completedAt;
    private volatile String failureReason;

    void addDependent(String id) { dependents.add(id); }
}