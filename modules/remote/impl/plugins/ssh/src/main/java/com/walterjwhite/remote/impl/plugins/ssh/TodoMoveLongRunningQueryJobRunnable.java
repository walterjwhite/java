package com.walterjwhite.remote.impl.plugins.ssh;

import com.walterjwhite.property.api.annotation.Property;
import com.walterjwhite.queue.api.annotation.Job;
import com.walterjwhite.queue.api.annotation.JobExecutionConfiguration;
import com.walterjwhite.queue.api.annotation.scheduling.AtDateAndTime;
import com.walterjwhite.queue.api.job.AbstractRunnable;
import com.walterjwhite.timeout.TimeConstrainedMethodInvocation;
import com.walterjwhite.timeout.annotation.TimeConstrained;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

/** This could be a queuedJob that pulls data from a database with a long-running query. */
@Job(
    jobExecutionConfiguration = @JobExecutionConfiguration(),
    atDateAndTime = @AtDateAndTime(hour = 7, recurrenceUnits = ChronoUnit.DAYS))
public class TodoMoveLongRunningQueryJobRunnable extends AbstractRunnable
    implements TimeConstrainedMethodInvocation {

  protected final ChronoUnit toDoMoveLongRunningQueryTimeoutUnits;
  protected final long toDoMoveLongRunningQueryTimeoutValue;

  public TodoMoveLongRunningQueryJobRunnable(
      @Property(TodoMoveLongRunningQueryTimeoutUnits.class)
          ChronoUnit toDoMoveLongRunningQueryTimeoutUnits,
      @Property(TodoMoveLongRunningQueryTimeoutValue.class)
          long toDoMoveLongRunningQueryTimeoutValue) {

    this.toDoMoveLongRunningQueryTimeoutUnits = toDoMoveLongRunningQueryTimeoutUnits;
    this.toDoMoveLongRunningQueryTimeoutValue = toDoMoveLongRunningQueryTimeoutValue;
  }

  @TimeConstrained
  @Override
  protected void doCall() {}

  @Override
  public Duration getAllowedExecutionDuration() {
    return Duration.of(toDoMoveLongRunningQueryTimeoutValue, toDoMoveLongRunningQueryTimeoutUnits);
  }
}
