package com.walterjwhite.examples.interruption;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import java.util.concurrent.Future;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

@NoArgsConstructor(onConstructor_ = @Inject)
public class InterruptableExampleCommandLineHandler implements CommandLineHandler {

  //  @Inject
  //  public InterruptableExampleCommandLineHandler(
  //      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
  //    super(shutdownTimeoutInSeconds);
  //  }

  @Override
  public void run(String... arguments) {
    final InterruptableServiceExample interruptableServiceExample =
        new InterruptableServiceExample();
    final Future f1 = interruptableServiceExample.run(new InterruptableProcessTaskExample());
    final Future f2 =
        interruptableServiceExample.run(new InterruptableTaskExample(Integer.MAX_VALUE));

    //    try{
    //      Thread.sleep(1000);
    //    } catch(InterruptedException e){
    //      LOGGER.warn("Unable to sleep", e);
    //    }
    //
    //    LOGGER.info("killing ")
  }
}
