package com.walterjwhite.inject.cli.service;

public interface DaemonCommandLineHandler extends CommandLineHandler {
  @Override
  default void run(String... arguments) throws Exception {
    Thread.currentThread().join();
  }
}
