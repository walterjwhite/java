package com.walterjwhite.data.pipe.modules.jdbc;

import com.walterjwhite.data.pipe.modules.jdbc.api.model.JDBCSourceConfiguration;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class JDBCIterator implements Iterator<Object[]>, AutoCloseable {
  protected final Connection connection;
  protected final JDBCSourceConfiguration JDBCSourceConfiguration;

  protected int columnCount = 10;
  protected PreparedStatement queryStatement;
  protected ResultSet resultSet;

  public JDBCIterator(Connection connection, JDBCSourceConfiguration JDBCSourceConfiguration)
      throws SQLException {
    this.connection = connection;
    this.JDBCSourceConfiguration = JDBCSourceConfiguration;

    queryStatement =
        connection.prepareStatement(JDBCSourceConfiguration.getJdbcQueryConfiguration().getQuery());
    prepareQuery();
    resultSet = queryStatement.executeQuery();
  }

  protected void prepareQuery() {}

  @Override
  public boolean hasNext() {
    try {
      return resultSet.next();
    } catch (SQLException e) {
      throw new RuntimeException("Error checking if there were more results");
    }
  }

  @Override
  public Object[] next() {
    if (!hasNext()) throw new NoSuchElementException("No more elements available");

    Object[] record = new Object[columnCount];
    for (int i = 0; i < columnCount; i++) {
      try {
        record[i] = resultSet.getObject(i);
      } catch (SQLException e) {
        throw new RuntimeException("Error retrieving", e);
      }
    }

    return record;
  }

  public void close() throws Exception {
    if (resultSet != null) {
      resultSet.close();
    }

    if (queryStatement != null) {
      queryStatement.close();
    }

    connection.close();
  }
}
