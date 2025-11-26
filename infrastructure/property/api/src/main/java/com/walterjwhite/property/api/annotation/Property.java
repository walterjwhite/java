package com.walterjwhite.property.api.annotation;


import com.walterjwhite.property.api.property.ConfigurableProperty;
import jakarta.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Qualifier
public @interface Property {
  Class<? extends ConfigurableProperty> value();
}
