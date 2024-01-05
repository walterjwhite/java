package com.walterjwhite.queue.api.model;

import java.util.concurrent.TimeUnit;
import lombok.*;

@ToString(doNotUseGetters = true)
// @Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleInstance implements Unqueueable {

  protected long initialDelay;

  protected long fixedDelay;

  protected TimeUnit units;

  //
  //
  //  protected ScheduleType scheduleType;

  public boolean isRecurring() {
    return fixedDelay > 0;
  }
}
