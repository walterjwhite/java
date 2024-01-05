package com.walterjwhite.infrastructure.datastore.modules.jpa.ddl.cli;

import com.walterjwhite.google.guice.cli.AbstractCommandLineModule;
import com.walterjwhite.infrastructure.datastore.modules.jpa.ddl.GenerateDDLModule;
import com.walterjwhite.property.api.PropertyManager;
import org.reflections.Reflections;

public class JPADDLCommandLineModule extends AbstractCommandLineModule {
  public JPADDLCommandLineModule(PropertyManager propertyManager, Reflections reflections) {
    super(propertyManager, reflections, JPADDLOperatingMode.class);
  }

  @Override
  protected void doCliConfigure() {
    install(new GenerateDDLModule());
  }
}
