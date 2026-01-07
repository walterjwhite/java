package com.walterjwhite.queue.api.annotation;

import com.walterjwhite.property.api.property.ConfigurableProperty;
import com.walterjwhite.queue.api.enumeration.Durability;
import com.walterjwhite.queue.api.property.JobRetryAttempts;
import com.walterjwhite.queue.api.property.JobTimeoutUnits;
import com.walterjwhite.queue.api.property.JobTimeoutValue;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JobExecutionConfiguration {
  @Deprecated
  Class<? extends ConfigurableProperty> timeoutUnits() default JobTimeoutUnits.class;

  @Deprecated
  Class<? extends ConfigurableProperty> timeoutValue() default JobTimeoutValue.class;

  Durability durability() default Durability.None;

  Class<? extends JobRetryAttempts> retryAttempts() default JobRetryAttempts.class;

  Class<? extends Throwable>[] retryFor() default RuntimeException.class;

  boolean system() default false;
}
