package com.walterjwhite.infrastructure.datastore.modules.jdbc.provider;

import com.walterjwhite.infrastructure.datastore.modules.jdbc.driver.DriverShim;
import com.walterjwhite.infrastructure.datastore.modules.jdbc.model.JDBCConfiguration;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.*;

public class JDBCAwareContainer implements AutoCloseable {
  protected Connection connection;

  public JDBCAwareContainer(JDBCConfiguration jdbcConfiguration) {
    this(jdbcConfiguration, true);
  }

  public JDBCAwareContainer(JDBCConfiguration jdbcConfiguration, final boolean autoCommit) {
    try (URLClassLoader urlClassLoader =
        new URLClassLoader(
            new URL[] {new File(jdbcConfiguration.getDriverPath()).toURI().toURL()},
            getClass().getClassLoader())) {
      final Driver driver =
          (Driver)
              Class.forName(jdbcConfiguration.getDriverClassName(), true, urlClassLoader)
                  .getDeclaredConstructor()
                  .newInstance();

      DriverManager.registerDriver(new DriverShim(driver));
      this.connection = JDBCConnectionProvider.get(jdbcConfiguration);
    } catch (Exception e) {
      throw new RuntimeException("Error configuring", e);
    }
  }

  @Override
  public void close() throws Exception {
    closeConnection();
  }

  protected void closeConnection() throws SQLException {
    connection.close();
  }

  public Connection getConnection() {
    return (connection);
  }
}
