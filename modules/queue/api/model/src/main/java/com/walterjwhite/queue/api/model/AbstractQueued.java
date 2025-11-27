package com.walterjwhite.queue.api.model;

import com.walterjwhite.queue.api.enumeration.QueueState;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class AbstractQueued implements Unqueueable {
  protected int id;

  protected transient boolean system;

  protected Class jobExecutorClass;

  protected Worker originatingWorker;

  @EqualsAndHashCode.Exclude protected LocalDateTime queueDateTime;

  @EqualsAndHashCode.Exclude @ToString.Exclude
  protected List<JobExecution> jobExecutions = new ArrayList<>();

  protected JobExecution currentJobExecution;

  @EqualsAndHashCode.Exclude protected Queue queue;

  protected QueueState queueState;
}
