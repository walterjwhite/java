package com.walterjwhite.queue.api.annotation.scheduling;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FixedDelay {
    long fixedDelay() default -1;

    long initialDelay() default -1;

    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
