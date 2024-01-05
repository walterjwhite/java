package com.walterjwhite.data.pipe.modules.jdbc.copy;

import com.walterjwhite.infrastructure.datastore.modules.jdbc.model.JDBCConfiguration;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class JDBCQueryConfiguration extends JDBCConfiguration {
  protected String queryFilename;
  protected String query;
  protected Object[] queryParameters;

  public JDBCQueryConfiguration(
      String username,
      String password,
      String jdbcUrl,
      String driverClassName,
      String driverPath,
      String queryFilename,
      String query,
      Object[] queryParameters) {
    super(username, password, jdbcUrl, driverClassName, driverPath);
    this.queryFilename = queryFilename;
    this.query = query;
    this.queryParameters = queryParameters;
  }

  public JDBCQueryConfiguration(
      String username, String password, String jdbcUrl, String driverClassName, String driverPath) {
    super(username, password, jdbcUrl, driverClassName, driverPath);
  }
}
