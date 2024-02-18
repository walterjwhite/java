package com.walterjwhite.metrics.annotation;

import java.lang.annotation.*;


@Deprecated
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Tag {
  String[] value();
}
