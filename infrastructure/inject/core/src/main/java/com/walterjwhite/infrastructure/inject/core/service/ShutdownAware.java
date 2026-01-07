package com.walterjwhite.infrastructure.inject.core.service;

public interface ShutdownAware extends AutoCloseable {
  void shutdown() throws Exception;
}
