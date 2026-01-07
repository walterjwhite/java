package com.walterjwhite.examples.platform.SPI;

import com.walterjwhite.examples.platform.SPI.api.SampleService;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;
import java.util.ServiceLoader;
import lombok.NoArgsConstructor;

@NoArgsConstructor(onConstructor_ = @Inject)
public class ExampleSPIAppHandler implements CommandLineHandler {
  protected int i = 0;


  @Override
  public void run(String... arguments) {
    final ServiceLoader<SampleService> sampleServices = ServiceLoader.load(SampleService.class);
    sampleServices.iterator().forEachRemaining(s -> {});
  }
}
