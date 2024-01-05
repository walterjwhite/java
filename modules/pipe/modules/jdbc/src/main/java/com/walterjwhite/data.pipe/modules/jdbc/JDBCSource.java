package com.walterjwhite.data.pipe.modules.jdbc;

import com.walterjwhite.data.pipe.impl.AbstractSource;
import com.walterjwhite.data.pipe.modules.jdbc.api.model.JDBCSourceConfiguration;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

/** TODO: 1. get the appropriate source connection here 2. get the appropriate source query here */
public class JDBCSource extends AbstractSource<Object[], JDBCSourceConfiguration> {
  protected PreparedStatementJDBCAwareContainer preparedStatementJDBCAwareContainer;
  protected ResultSet resultSet;

  @Override
  protected void doConfigure() {
    preparedStatementJDBCAwareContainer =
        new PreparedStatementJDBCAwareContainer(sourceConfiguration.getJdbcQueryConfiguration());
    try {
      resultSet = preparedStatementJDBCAwareContainer.getPreparedStatement().executeQuery();
    } catch (SQLException e) {
      throw new RuntimeException("Error preparing resultset", e);
    }
  }

  public void close() throws Exception {
    closeResultSet();
    preparedStatementJDBCAwareContainer.close();
  }

  protected void closeResultSet() throws SQLException {
    if (resultSet != null) {

      resultSet.close();
    }
  }

  @Override
  public Iterator<Object[]> iterator() {
    try {
      return new JDBCIterator(
          preparedStatementJDBCAwareContainer.getConnection(), sourceConfiguration);
    } catch (SQLException e) {
      throw new RuntimeException("Error initializing iterator", e);
    }
  }
}
