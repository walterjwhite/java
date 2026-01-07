package com.walterjwhite.infrastructure.datastore.modules.jdbc.run;

import com.walterjwhite.infrastructure.datastore.modules.jdbc.run.service.RunSQLService;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;

public class JDBCRunCommandLineHandler implements CommandLineHandler {
  protected final RunSQLService runSQLService;

  @Inject
  public JDBCRunCommandLineHandler(
      RunSQLService runSQLService) {
    this.runSQLService = runSQLService;
  }

  @Override
  public void run(String... arguments) throws Exception {
    runSQLService.run();
  }
}
