package com.walterjwhite.email.api.service.selector.concept;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ProviderMapSupports {
  Class argumentType();

  Class<? extends ProviderType> providerType();
}
