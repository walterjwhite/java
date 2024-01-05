package com.walterjwhite.infrastructure.datastore.modules.jdbc.driver;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Since we're not bundling the JDBC driver, we need to load this dynamically, this let's us do
 * that.
 */
public class DriverShim implements Driver {
  protected final Driver driver;

  public DriverShim(Driver driver) {

    this.driver = driver;
  }

  @Override
  public Connection connect(String s, Properties properties) throws SQLException {
    return driver.connect(s, properties);
  }

  @Override
  public boolean acceptsURL(String s) throws SQLException {
    return driver.acceptsURL(s);
  }

  @Override
  public DriverPropertyInfo[] getPropertyInfo(String s, Properties properties) throws SQLException {
    return driver.getPropertyInfo(s, properties);
  }

  @Override
  public int getMajorVersion() {
    return driver.getMajorVersion();
  }

  @Override
  public int getMinorVersion() {
    return driver.getMinorVersion();
  }

  @Override
  public boolean jdbcCompliant() {
    return driver.jdbcCompliant();
  }

  @Override
  public Logger getParentLogger() throws SQLFeatureNotSupportedException {
    return driver.getParentLogger();
  }
}
