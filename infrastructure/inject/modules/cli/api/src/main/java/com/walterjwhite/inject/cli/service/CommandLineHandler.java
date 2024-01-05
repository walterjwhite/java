package com.walterjwhite.inject.cli.service;

/*
 * TODO: invoke all shutdown aware hooks (on shutdown)
 */
public interface CommandLineHandler {
  //  protected final long shutdownTimeoutInSeconds;

  //  AbstractCommandLineHandler(final int shutdownTimeoutInSeconds) {
  //    this.shutdownTimeoutInSeconds = shutdownTimeoutInSeconds;
  //  }
  // final int shutdownTimeout = PropertyManager.get(ShutdownTimeout.class)

  void run(final String... arguments) throws Exception;
}
