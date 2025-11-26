package com.walterjwhite.property.api.annotation;

import com.walterjwhite.property.api.property.ConfigurableProperty;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Deprecated
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.LOCAL_VARIABLE, ElementType.FIELD, ElementType.PARAMETER})
public @interface QualifierType {
  Class<? extends ConfigurableProperty> value();
}
