/*
package com.walterjwhite.data.pipe.modules.jdbc.copy;

import com.walterjwhite.google.guice.GuiceHelper;
import com.walterjwhite.google.guice.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.serialization.api.service.SerializationService;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

public class DatabaseCopierCommandLineHandler extends AbstractCommandLineHandler {
  @Override
  public void run(String... arguments) throws Exception {
    for (final String argument : arguments) {
      copyDatabase(argument);
    }
  }

  private static void copyDatabase(final String argument) throws IOException {
    SerializationService serializationService =
        GuiceHelper.getGuiceApplicationInjector().getInstance(SerializationService.class);
    DatabaseCopySession databaseCopySession =
        (DatabaseCopySession)
            serializationService.deserialize(
                new FileInputStream(argument), DatabaseCopySession.class);
    for (final String tableName : databaseCopySession.getTableNames()) {
      copyTable(databaseCopySession, tableName);
    }
  }

  private static void copyTable(DatabaseCopySession databaseCopySession, final String tableName) {
    GuiceHelper.getGuiceApplicationInjector()
        .getInstance(ExecutorService.class)
        .submit(
            new DatabaseTableCopierCallable(
                new TableCopy(tableName, "table - " + tableName, databaseCopySession)));
  }
}
*/
