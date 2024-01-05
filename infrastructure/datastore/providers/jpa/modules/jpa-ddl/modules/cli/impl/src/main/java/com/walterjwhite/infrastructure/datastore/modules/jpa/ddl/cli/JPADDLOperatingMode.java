package com.walterjwhite.infrastructure.datastore.modules.jpa.ddl.cli;

import com.walterjwhite.google.guice.cli.property.OperatingMode;
import com.walterjwhite.google.guice.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.api.annotation.DefaultValue;

public enum JPADDLOperatingMode implements OperatingMode {
  @DefaultValue
  Default(JPADDLCommandLineHandler.class);

  private final Class<? extends AbstractCommandLineHandler> initiatorClass;

  JPADDLOperatingMode(Class<? extends AbstractCommandLineHandler> initiatorClass) {
    this.initiatorClass = initiatorClass;
  }

  @Override
  public Class<? extends AbstractCommandLineHandler> getInitiatorClass() {
    return initiatorClass;
  }
}
