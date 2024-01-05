package com.walterjwhite.file.modules.watcher;

/** Interface definition for services. */
public interface Service {
  /** Starts the service. This method blocks until the service has completely started. */
  void start();

  /** Stops the service. This method blocks until the service has completely shut down. */
  void stop();
}
