package com.walterjwhite.queue.api.model;

import java.time.LocalDateTime;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class QueuedJob extends AbstractQueued {
  protected ScheduleInstance scheduleInstance;

  public QueuedJob(Class jobExecutorClass) {
    this(jobExecutorClass, null);
  }

  public QueuedJob(Class jobExecutorClass, ScheduleInstance scheduleInstance) {
    this(jobExecutorClass, scheduleInstance, false);
  }

  public QueuedJob(
      Class jobExecutorClass, ScheduleInstance scheduleInstance, final boolean system) {
    this();
    this.jobExecutorClass = jobExecutorClass;
    this.scheduleInstance = scheduleInstance;
    this.queueDateTime = LocalDateTime.now();
    this.system = system;
  }

}
