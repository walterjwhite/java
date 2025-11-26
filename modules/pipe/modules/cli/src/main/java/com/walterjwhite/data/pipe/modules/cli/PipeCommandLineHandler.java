package com.walterjwhite.data.pipe.modules.cli;

import com.walterjwhite.data.pipe.api.session.PipeSessionConfiguration;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.serialization.modules.snakeyaml.Snakeyaml;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Set;

public class PipeCommandLineHandler implements CommandLineHandler, AutoCloseable {
  protected final Snakeyaml serializationService;

  protected Set<PipeSessionInstance> pipeSessionInstances = new HashSet<>();

  public PipeCommandLineHandler(/*int shutdownTimeoutInSeconds, */ Snakeyaml serializationService) {
    this.serializationService = serializationService;
  }

  @Override
  public void run(String... arguments) throws Exception {
    for (final String argument : arguments) {
      PipeSessionConfiguration pipeSessionConfiguration =
          serializationService.deserialize(
              new BufferedInputStream(new FileInputStream(argument)),
              PipeSessionConfiguration.class);
      final PipeSessionInstance pipeSessionInstance =
          new PipeSessionInstance(pipeSessionConfiguration);
      pipeSessionInstances.add(pipeSessionInstance);
      pipeSessionInstance.configure();
      pipeSessionInstance.start();
    }
  }

  @Override
  public void close() {
    pipeSessionInstances.stream().forEach(pipeSessionInstance -> pipeSessionInstance.stop());
  }
}
