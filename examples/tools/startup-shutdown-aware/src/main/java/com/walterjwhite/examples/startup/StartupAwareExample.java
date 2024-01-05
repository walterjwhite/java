package com.walterjwhite.examples.startup;

import com.walterjwhite.infrastructure.inject.core.service.StartupAware;

public class StartupAwareExample implements StartupAware {
  @Override
  public void startup() {
    throw new RuntimeException("Testing startup failure");
  }

  @Override
  public void close() {}
}
