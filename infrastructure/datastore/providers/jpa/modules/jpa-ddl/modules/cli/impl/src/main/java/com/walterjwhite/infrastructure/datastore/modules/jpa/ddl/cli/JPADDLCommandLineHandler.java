package com.walterjwhite.infrastructure.datastore.modules.jpa.ddl.cli;

import com.walterjwhite.google.guice.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.google.guice.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.infrastructure.datastore.modules.jpa.ddl.service.GenerateDDLService;
import com.walterjwhite.property.impl.annotation.Property;
import jakarta.inject.Inject;

public class JPADDLCommandLineHandler extends AbstractCommandLineHandler {
  protected final GenerateDDLService generateDDLService;

  @Inject
  public JPADDLCommandLineHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) final int shutdownTimeoutInSeconds,
      GenerateDDLService generateDDLService) {
    super(shutdownTimeoutInSeconds);

    this.generateDDLService = generateDDLService;
  }

  @Override
  public void run(String... arguments) throws Exception {
    generateDDLService.generateDDL();
  }

  @Override
  protected void onShutdown() throws Exception {}
}
