package com.walterjwhite.infrastructure.datastore.modules.jdbc.provider;

import com.walterjwhite.infrastructure.datastore.modules.jdbc.model.JDBCConfiguration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnectionProvider {
  public static Connection get(JDBCConfiguration jdbcConfiguration) throws SQLException {

    return (DriverManager.getConnection(
        jdbcConfiguration.getJdbcUrl(),
        jdbcConfiguration.getUsername(),
        jdbcConfiguration.getPassword()));
  }
}
