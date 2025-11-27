package com.walterjwhite.queue.api.annotation;

import com.walterjwhite.queue.api.annotation.scheduling.AtDateAndTime;
import com.walterjwhite.queue.api.annotation.scheduling.Cron;
import com.walterjwhite.queue.api.annotation.scheduling.FixedDelay;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Job {
  JobExecutionConfiguration jobExecutionConfiguration();

  FixedDelay[] fixedDelay() default {};

  AtDateAndTime[] atDateAndTime() default {};

  Cron[] cron() default {};

}
