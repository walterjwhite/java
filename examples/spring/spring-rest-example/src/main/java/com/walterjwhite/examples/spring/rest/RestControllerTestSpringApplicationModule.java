package com.walterjwhite.examples.spring.rest;

import com.walterjwhite.infrastructure.inject.providers.spring.SpringApplicationModule;

public class RestControllerTestSpringApplicationModule implements SpringApplicationModule {
  @Override
  public String[] scanBasePackages() {
    return new String[] {"com.walterjwhite"};
  }
}
