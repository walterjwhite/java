package com.walterjwhite.examples.spring.batch_simple;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class BatchJob {

  private final String id;
  private final String name;
  private final int durationSeconds; // simulated execution time
  private final Set<String> dependencies;
  private final Set<String> dependents = new HashSet<>();

  private volatile JobStatus status = JobStatus.PENDING;
  private volatile boolean wantToRun = false;
  private volatile Instant submittedAt;
  private volatile Instant startedAt;
  private volatile Instant completedAt;
  private volatile String failureReason;

  public void addDependent(String id) {
    dependents.add(id);
  }
}
