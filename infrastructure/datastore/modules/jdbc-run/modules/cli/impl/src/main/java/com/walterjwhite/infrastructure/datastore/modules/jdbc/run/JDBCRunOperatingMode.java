package com.walterjwhite.infrastructure.datastore.modules.jdbc.run;

import com.walterjwhite.inject.cli.property.OperatingMode;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.property.api.annotation.DefaultValue;

public enum JDBCRunOperatingMode implements OperatingMode {
  @DefaultValue
  Default(JDBCRunCommandLineHandler.class);

  private final Class<? extends CommandLineHandler> initiatorClass;

  JDBCRunOperatingMode(Class<? extends CommandLineHandler> initiatorClass) {
    this.initiatorClass = initiatorClass;
  }

  @Override
  public Class<? extends CommandLineHandler> getInitiatorClass() {
    return initiatorClass;
  }
}
