package com.walterjwhite.metrics.annotation;

import com.walterjwhite.metrics.enumeration.MetricsType;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Metrics {
  MetricsType[] value();
}
