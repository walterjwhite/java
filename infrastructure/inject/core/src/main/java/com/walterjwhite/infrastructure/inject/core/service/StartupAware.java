package com.walterjwhite.infrastructure.inject.core.service;

// first, then the dependent one last
public interface StartupAware extends AutoCloseable {
  void startup() throws Exception;
}
