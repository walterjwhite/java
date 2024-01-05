package com.walterjwhite.examples.platform.SPI;

import com.walterjwhite.examples.platform.SPI.api.SampleService;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import java.util.ServiceLoader;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

@NoArgsConstructor(onConstructor_ = @Inject)
public class ExampleSPIAppHandler implements CommandLineHandler {
  protected int i = 0;

  //  @Inject
  //  public ExampleSPIAppHandler(
  //      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
  //    super(shutdownTimeoutInSeconds);
  //  }

  @Override
  public void run(String... arguments) {
    final ServiceLoader<SampleService> sampleServices = ServiceLoader.load(SampleService.class);
    sampleServices.iterator().forEachRemaining(s -> {});
  }
}
