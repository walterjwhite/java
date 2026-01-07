package com.walterjwhite.examples.interruption;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;
import java.util.concurrent.Future;
import lombok.NoArgsConstructor;

@NoArgsConstructor(onConstructor_ = @Inject)
public class InterruptableExampleCommandLineHandler implements CommandLineHandler {


  @Override
  public void run(String... arguments) {
    final InterruptableServiceExample interruptableServiceExample =
        new InterruptableServiceExample();
    final Future f1 = interruptableServiceExample.run(new InterruptableProcessTaskExample());
    final Future f2 =
        interruptableServiceExample.run(new InterruptableTaskExample(Integer.MAX_VALUE));

  }
}
