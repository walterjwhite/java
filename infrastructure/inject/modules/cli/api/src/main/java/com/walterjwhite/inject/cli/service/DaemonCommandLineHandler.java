package com.walterjwhite.inject.cli.service;

public interface DaemonCommandLineHandler extends CommandLineHandler {
  //  DaemonCommandLineHandler(final int shutdownTimeoutInSeconds) {
  //    super(shutdownTimeoutInSeconds);
  //  }

  @Override
  default void run(String... arguments) throws Exception {
    Thread.currentThread().join();
  }
}
