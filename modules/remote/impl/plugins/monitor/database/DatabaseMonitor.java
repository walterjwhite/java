package com.walterjwhite.remote.impl.service.database;

import com.walterjwhite.remote.impl.service.configuration.DatabaseConfiguration;
import com.walterjwhite.remote.impl.service.monitor.AbstractMonitor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.yaml.snakeyaml.Yaml;

/** result can be: a single value a row with many columns (and column headers) */
@Data
@ToString(doNotUseGetters = true)
public class DatabaseMonitor extends AbstractMonitor<DataTable> {
  protected final Connection connection;

  /** SQL file containing the query to run */
  protected String queryFile;

  /** Parameters to the query above. */
  protected String[] parameters;

  /*
  protected DatabaseMonitor(int refreshInterval, final String[] columnNames) throws ClassNotFoundException, SQLException, IOException {
      super(refreshInterval, new DataTable(columnNames));

      configuration.load(DatabaseMonitor.class.getResourceAsStream("database.properties"));

      Class.forName(configuration.getProperty("DATABASE_DRIVER"));
      connection = DriverManager.getConnection(configuration.getProperty("DATABASE_URI"), configuration.getProperty("DATABASE_USERNAME"), configuration.getProperty("DATABASE_PASSWORD"));

  }
   */

  public DatabaseMonitor() throws ClassNotFoundException, SQLException, IOException {
    super(new DataTable());

    Yaml yaml = new Yaml();
    try (InputStream in = Files.newInputStream(Paths.get("database.yaml"))) {
      final DatabaseConfiguration config = yaml.loadAs(in, DatabaseConfiguration.class);

      Class.forName(config.getDatabaseDriver());
      connection =
          DriverManager.getConnection(
              config.getDatabaseUri(), config.getDatabaseUsername(), config.getDatabasePassword());
    }
  }

  protected PreparedStatement setup() throws SQLException, IOException {
    final PreparedStatement statement = connection.prepareStatement(getQuery());

    if (parameters != null) {
      for (int i = 0; i < parameters.length; i++) {
        statement.setString(i + 1, parameters[i]);
      }
    }

    return (statement);
  }

  protected String getQuery() throws IOException {
    return IOUtils.toString(new BufferedInputStream(new FileInputStream(new File(queryFile))));
  }

  @Override
  public Void call() throws Exception {
    try (final PreparedStatement statement = setup()) {
      try (ResultSet resultSet = statement.executeQuery()) {
        setupColumnNames(resultSet);
        result.clear();

        while (resultSet.next()) {
          // final String[] data = new String[fields.length];
          final List<String> row = new ArrayList<String>();
          for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
            row.add(resultSet.getString(i + 1));
          }

          result.add(row);
        }
      }

      return (null);
    }
  }

  protected void setupColumnNames(final ResultSet resultSet) throws SQLException {
    if (result.columnNames == null) {
      // set the column names
      final List<String> columnNames = new ArrayList<String>();

      for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
        columnNames.add(resultSet.getMetaData().getColumnName(i + 1));
      }

      result.setColumnNames(columnNames);
    }
  }
}
