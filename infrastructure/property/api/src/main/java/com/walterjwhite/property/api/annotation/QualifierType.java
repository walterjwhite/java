package com.walterjwhite.property.api.annotation;

import com.walterjwhite.property.api.property.ConfigurableProperty;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a value as the default if one is not explicitly set via command-line, environment, or
 * properties file.
 */
// unused currently, see: @Primary / @Secondary, still experimenting with which direction to take
@Deprecated
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.LOCAL_VARIABLE, ElementType.FIELD, ElementType.PARAMETER})
public @interface QualifierType {
  Class<? extends ConfigurableProperty> value();
}
