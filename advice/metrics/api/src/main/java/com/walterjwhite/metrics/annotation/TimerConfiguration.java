package com.walterjwhite.metrics.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TimerConfiguration {
  String description() default "";

  String[] tags() default {};
}
