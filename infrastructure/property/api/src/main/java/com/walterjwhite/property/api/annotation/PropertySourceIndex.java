package com.walterjwhite.property.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Used to order the property source. TODO: consider allowing this to be overridden at runtime? */
// to control ordering of multi-bindings
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.LOCAL_VARIABLE, ElementType.FIELD})
public @interface PropertySourceIndex {
  int value();
}
