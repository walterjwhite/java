package com.walterjwhite.data.pipe.modules.cli;

import com.walterjwhite.data.pipe.impl.DataPipeModule;
import com.walterjwhite.google.guice.cli.AbstractCommandLineModule;
import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.serialization.modules.snakeyaml.SnakeyamlSerializationServiceModule;
import org.reflections.Reflections;

public class DataPipeCLIModule extends AbstractCommandLineModule {
  public DataPipeCLIModule(PropertyManager propertyManager, Reflections reflections) {
    super(propertyManager, reflections, PipeOperatingMode.class);
  }

  @Override
  protected void doCliConfigure() {
    install(new DataPipeModule());
    install(new SnakeyamlSerializationServiceModule());
  }
}
