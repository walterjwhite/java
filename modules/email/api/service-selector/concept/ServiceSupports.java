package com.walterjwhite.email.api.service.selector.legacy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ServiceSupports {
  //    Enum[] value();
  Class<? extends ProviderType> value();
}
