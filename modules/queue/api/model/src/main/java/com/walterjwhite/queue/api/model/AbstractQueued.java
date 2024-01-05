package com.walterjwhite.queue.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.queue.api.enumeration.QueueState;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// @NoArgsConstructor
@Data
@ToString(doNotUseGetters = true)

// @Embeddable
// @MappedSuperclass
public class AbstractQueued extends AbstractEntity implements Unqueueable {
  protected transient boolean system;

  protected Class jobExecutorClass;

  protected Worker originatingWorker;

  @EqualsAndHashCode.Exclude protected LocalDateTime queueDateTime;

  @EqualsAndHashCode.Exclude @ToString.Exclude
  protected List<JobExecution> jobExecutions = new ArrayList<>();

  protected JobExecution currentJobExecution;

  @EqualsAndHashCode.Exclude protected Queue queue;

  protected QueueState queueState;
  //  /**
  //   * Used by the workers to determine if the job can be picked up for assignment.
  //   *
  //   * @return
  //   */
  //  public boolean isAssignable() {
  //    return getExecutionState().isAssignable();
  //  }
  //
  //  public ExecutionState getExecutionState() {
  ////    if (jobExecutions == null || jobExecutions.isEmpty()) return ExecutionState.Queued;
  ////
  ////    return jobExecutions.get(jobExecutions.size() - 1).getExecutionState();
  //    if(currentJobExecution == null) return ExecutionState.Queued;
  //
  //    return currentJobExecution.getExecutionState();
  //  }
}
