package com.walterjwhite.transform.annotation;

import com.walterjwhite.transform.configuration.TransformInstanceConfiguration;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Transformer {
  Class<? extends TransformInstanceConfiguration> configurationClass();

  String pattern();

  String method() default "init";
}
