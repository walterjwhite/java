package com.walterjwhite.logging.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogRate {
  double value() default com.walterjwhite.logging.enumeration.LogRate.Default;
}
