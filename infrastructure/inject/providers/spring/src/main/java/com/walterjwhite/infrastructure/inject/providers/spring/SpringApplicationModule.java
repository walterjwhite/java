package com.walterjwhite.infrastructure.inject.providers.spring;

public interface SpringApplicationModule {
  default String[] scanBasePackages() {
    return null;
  }
}
