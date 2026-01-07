package com.walterjwhite.infrastructure.datastore.modules.jdbc.run.service;

import com.walterjwhite.infrastructure.datastore.modules.jdbc.model.JDBCConfiguration;
import com.walterjwhite.infrastructure.datastore.modules.jdbc.provider.JDBCAwareContainer;
import com.walterjwhite.infrastructure.datastore.modules.jdbc.run.model.MasterRunScript;
import com.walterjwhite.infrastructure.datastore.modules.jdbc.run.property.*;
import com.walterjwhite.property.api.annotation.Property;
import com.walterjwhite.property.api.enumeration.Debug;
import com.walterjwhite.serialization.api.service.SerializationService;
import jakarta.inject.Inject;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.CallableStatement;
import java.sql.SQLException;
import org.apache.commons.io.IOUtils;

public class RunSQLService {
  protected final SerializationService serializationService;
  protected final MasterRunScript masterRunScript;
  protected final JDBCAwareContainer jdbcAwareContainer;
  protected final boolean debug;

  @Inject
  public RunSQLService(
      @Property(MasterRunScriptPath.class) String masterRunScriptPath,
      SerializationService serializationService,
      @Property(JDBCDriverClassName.class) String jdbcDriverClassname,
      @Property(JDBCUrl.class) String jdbcUrl,
      @Property(JDBCUsername.class) String jdbcUsername,
      @Property(JDBCPassword.class) String jdbcPassword,
      @Property(JDBCDriverPath.class) String jdbcDriverPath,
      @Property(Debug.class) boolean debug)
      throws IOException {
    this.serializationService = serializationService;

    this.masterRunScript =
        serializationService.deserialize(
            new BufferedInputStream(new FileInputStream(masterRunScriptPath)),
            MasterRunScript.class);
    this.debug = debug;

    if (debug) {
      jdbcAwareContainer = null;
    } else {
      jdbcAwareContainer =
          new JDBCAwareContainer(
              new JDBCConfiguration(
                  jdbcUsername, jdbcPassword, jdbcUrl, jdbcDriverClassname, jdbcDriverPath));
    }
  }

  public void run() throws IOException, SQLException {
    try {
      execute();
    } finally {
      close();
    }
  }

  protected void execute() throws IOException, SQLException {
    for (final String sourceFilename : masterRunScript.getScriptFilenames()) {
      final String scriptContents =
          IOUtils.toString(
              new BufferedInputStream(new FileInputStream(sourceFilename)),
              Charset.defaultCharset());
      for (final String statement : getStatements(scriptContents)) {
        execute(statement);
      }
    }
  }

  protected String[] getStatements(final String input) {
    return input.split(";");
  }

  protected boolean execute(final String statement) throws SQLException {
    if (debug) {
      return false;
    }

    try (final CallableStatement callableStatement =
        jdbcAwareContainer.getConnection().prepareCall(statement)) {
      return callableStatement.execute();
    }
  }

  public void close() throws SQLException {
    if (debug) {
      if (jdbcAwareContainer != null) {
        if (jdbcAwareContainer.getConnection() != null) {

          jdbcAwareContainer.getConnection().commit();


          jdbcAwareContainer.getConnection().close();
        }
      }
    }
  }

  @Override
  protected void finalize() throws Throwable {
    close();

    super.finalize();
  }

}
