package com.walterjwhite.queue.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
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
public class JobExecution extends AbstractEntity implements Unqueueable {

  protected AbstractQueued queued;

  protected int attemptIndex;

  @EqualsAndHashCode.Exclude protected LocalDateTime startDateTime;

  @EqualsAndHashCode.Exclude protected LocalDateTime endDateTime;

  @EqualsAndHashCode.Exclude protected LocalDateTime timeoutDateTime;

  /**
   * Used by the worker to indicate it is still working If the worker dies unexpectedly, this will
   * be compared against the current time and a given timeout. if greater than that, then this job
   * is marked as aborted and retried
   */
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
