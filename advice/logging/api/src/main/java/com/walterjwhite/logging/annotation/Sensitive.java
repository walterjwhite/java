package com.walterjwhite.logging.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({
  ElementType.TYPE,
  ElementType.LOCAL_VARIABLE,
  ElementType.FIELD,
  ElementType.METHOD,
  ElementType.CONSTRUCTOR,
  ElementType.PARAMETER
})
public @interface Sensitive {
  int value() default 4;
}
