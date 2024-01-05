package com.walterjwhite.examples.keep_alive;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.keep_alive.KeepAliveHelper;
import com.walterjwhite.keep_alive.KeepAliveable;
import java.util.concurrent.ExecutionException;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

@NoArgsConstructor(onConstructor_ = @Inject)
public class KeepAliveExampleCommandLineHandler
    implements CommandLineHandler, KeepAliveable<KeepAliveExample> {
  protected int i = 0;

  //  @Inject
  //  public KeepAliveExampleCommandLineHandler(
  //      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
  //    super(shutdownTimeoutInSeconds);
  //  }

  @Override
  public void run(String... arguments) throws ExecutionException, InterruptedException {
    KeepAliveHelper.keepAlive(this, new KeepAliveExample("Fred"));
  }

  @Override
  public void onKeepAlive(KeepAliveExample data) {}
}
