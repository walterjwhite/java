package com.walterjwhite.infrastructure.inject.core.service;

public interface StartupAware extends AutoCloseable {
  void startup() throws Exception;
}
