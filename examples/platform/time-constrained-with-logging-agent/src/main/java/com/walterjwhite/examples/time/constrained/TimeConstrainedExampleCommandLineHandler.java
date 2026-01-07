package com.walterjwhite.examples.time.constrained;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

@NoArgsConstructor(onConstructor_ = @Inject)
public class TimeConstrainedExampleCommandLineHandler implements CommandLineHandler {


  @Override
  public void run(String... arguments) throws InterruptedException {
    int i = 0;

    while (true) {
      Thread.sleep(50);
    }
  }
}
