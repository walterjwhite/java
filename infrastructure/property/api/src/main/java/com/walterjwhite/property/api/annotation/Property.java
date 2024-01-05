package com.walterjwhite.property.api.annotation;

// import com.google.inject.BindingAnnotation;

import com.walterjwhite.property.api.property.ConfigurableProperty;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.inject.Qualifier;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
// @BindingAnnotation
// use standard CDI and remove guice-specific
// NOTE: that this has always worked with guice and never have been an issue, is it officially
// supported on guice?
// NOTE: that on CDI, I believe it should have an AnnotationLiteral reference
@Qualifier
public @interface Property {
  Class<? extends ConfigurableProperty> value();
}
