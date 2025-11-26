package com.walterjwhite.examples.startup;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

@NoArgsConstructor(onConstructor_ = @Inject)
public class StartupExampleCommandLineHandler implements CommandLineHandler {
  @Override
  public void run(String... arguments) throws InterruptedException {
    new ShutdownAwareExample();

    Thread.sleep(1000);
  }
}
