package com.walterjwhite.data.pipe.modules.jdbc.copy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;

public class DatabaseTableCopierCallable implements Callable<Void> {
  protected final TableCopy tableCopy;

  public DatabaseTableCopierCallable(TableCopy tableCopy) {

    this.tableCopy = tableCopy;
  }

  @Override
  public Void call() throws Exception {
    try (final Connection sourceConnection = getSourceConnection()) {
      final List<Column> columns = printColumnNames(sourceConnection);

      try (final Connection targetConnection = getTargetConnection()) {
        createTable(targetConnection, columns);
      }
    }

    return null;
  }

  protected Connection getSourceConnection() throws ClassNotFoundException, SQLException {
    Class.forName(tableCopy.databaseCopySession.getSourceConfiguration().getDriverClassName());

    if (tableCopy.getDatabaseCopySession().getSourceConfiguration().getProperties() != null) {
      return (DriverManager.getConnection(
          tableCopy.getDatabaseCopySession().getSourceConfiguration().getJdbcUrl(),
          getProperties(
              tableCopy.getDatabaseCopySession().getSourceConfiguration().getProperties())));
    }

    return (DriverManager.getConnection(
        tableCopy.getDatabaseCopySession().getSourceConfiguration().getJdbcUrl(),
        tableCopy.getDatabaseCopySession().getSourceConfiguration().getUsername(),
        tableCopy.getDatabaseCopySession().getSourceConfiguration().getPassword()));
  }

  protected Connection getTargetConnection() {
    return (null);
  }

  protected void printTableNames(Connection sourceConnection) throws SQLException {
    final DatabaseMetaData databaseMetaData = sourceConnection.getMetaData();

    try (final ResultSet sourceTableResultSet =
        databaseMetaData.getTables(null, null, null, new String[] {"TABLE"})) {
      while (sourceTableResultSet.next()) {
        printTable(sourceTableResultSet);
      }
    }
  }

  protected void printTable(final ResultSet sourceTableResultSet) {
  }

  protected List<Column> printColumnNames(Connection sourceConnection) throws SQLException {
    final List<Column> columns = new ArrayList<>();

    final DatabaseMetaData databaseMetaData = sourceConnection.getMetaData();

    try (final ResultSet sourceTableResultSet =
        databaseMetaData.getColumns(null, null, tableCopy.getName(), null)) {
      while (sourceTableResultSet.next()) {
        columns.add(
            new Column(
                sourceTableResultSet.getString("COLUMN_NAME"),
                JDBCType.valueOf(sourceTableResultSet.getString("DATA_TYPE")),
                Integer.valueOf(sourceTableResultSet.getString("COLUMN_SIZE")),
                Integer.valueOf(sourceTableResultSet.getString("DECIMAL_DIGITS")),
                Boolean.valueOf(sourceTableResultSet.getString("IS_NULLABLE")),
                Boolean.valueOf(sourceTableResultSet.getString("IS_AUTOINCREMENT"))));
      }
    }

    return (columns);
  }

  protected void createTable(Connection targetConnection, final List<Column> columns)
      throws SQLException {
    final StringBuilder buffer = new StringBuilder();

    buffer.append("CREATE TABLE " + tableCopy.getName() + " (");

    for (int i = 0; i < columns.size(); i++) {
      final Column column = columns.get(i);

      buffer.append(column.getName() + " ");
      if (JDBCType.TINYINT.equals(column.getType())) {
        buffer.append(JDBCType.INTEGER.getName());
      } else {
        buffer.append(column.getType().getName());

        if (JDBCType.VARCHAR.equals(column.getType())) {
          if (column.getSize() > 0) {
            buffer.append("(" + column.getSize());

            if (column.getDecimalDigits() > 0) buffer.append("," + column.getDecimalDigits());

            buffer.append(")");
          }
        }
      }

      if (i + 1 < columns.size()) buffer.append(",\n");
    }

    buffer.append(")");

    try (final CallableStatement callableStatement =
        targetConnection.prepareCall(buffer.toString())) {
      callableStatement.executeUpdate();
    }
  }

  protected Properties getProperties(final Map<String, String> databaseProperties) {
    final Properties properties = new Properties();

    for (final String key : databaseProperties.keySet()) {
      properties.put(key, databaseProperties.get(key));
    }

    return (properties);
  }
}
