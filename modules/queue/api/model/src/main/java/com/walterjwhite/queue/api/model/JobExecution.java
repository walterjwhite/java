package com.walterjwhite.queue.api.model;

import com.walterjwhite.queue.api.enumeration.ExecutionState;
import java.time.LocalDateTime;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
@NoArgsConstructor
public class JobExecution implements Unqueueable {
  protected int id;
  protected AbstractQueued queued;

  protected int attemptIndex;

  @EqualsAndHashCode.Exclude protected LocalDateTime startDateTime;

  @EqualsAndHashCode.Exclude protected LocalDateTime endDateTime;

  @EqualsAndHashCode.Exclude protected LocalDateTime timeoutDateTime;

  @EqualsAndHashCode.Exclude protected LocalDateTime updateDateTime;

  @EqualsAndHashCode.Exclude protected Throwable throwable;

  protected ExecutionState executionState;

  @EqualsAndHashCode.Exclude protected Worker worker;

  public JobExecution(AbstractQueued queued) {

    this.queued = queued;
    this.attemptIndex = queued.getJobExecutions().size();
    this.startDateTime = LocalDateTime.now();
  }
}
