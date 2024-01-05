package com.walterjwhite.data.pipe.modules.jdbc;

import com.walterjwhite.data.pipe.modules.jdbc.copy.JDBCQueryConfiguration;
import com.walterjwhite.infrastructure.datastore.modules.jdbc.provider.JDBCAwareContainer;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatementJDBCAwareContainer extends JDBCAwareContainer {
  protected PreparedStatement preparedStatement;

  public PreparedStatementJDBCAwareContainer(JDBCQueryConfiguration jdbcQueryConfiguration) {
    this(jdbcQueryConfiguration, true);
  }

  public PreparedStatementJDBCAwareContainer(
      JDBCQueryConfiguration jdbcQueryConfiguration, final boolean autoCommit) {
    super(jdbcQueryConfiguration, autoCommit);
    try {
      this.preparedStatement = connection.prepareStatement(jdbcQueryConfiguration.getQuery());

      setQueryParameters(jdbcQueryConfiguration);
    } catch (Exception e) {
      throw new RuntimeException("Error configuring", e);
    }
  }

  protected void setQueryParameters(JDBCQueryConfiguration jdbcConfiguration) throws SQLException {
    if (jdbcConfiguration.getQueryParameters() != null
        && jdbcConfiguration.getQueryParameters().length > 0) {
      for (int i = 0; i < jdbcConfiguration.getQueryParameters().length; i++) {
        final Object argument = jdbcConfiguration.getQueryParameters()[i];

        if (argument == null) this.preparedStatement.setObject(i + 1, null);
        else if (argument instanceof String)
          this.preparedStatement.setString(i + 1, (String) argument);
        else if (argument instanceof Integer)
          this.preparedStatement.setInt(i + 1, (Integer) argument);
        else if (argument instanceof Double)
          this.preparedStatement.setDouble(i + 1, (Double) argument);
        //        else if(argument instanceof LocalDate)
        //          this.preparedStatement.setDate(i + 1, ((LocalDate)argument).);
        else throw new IllegalArgumentException(argument.getClass() + " is unsupported");
      }
    }
  }

  @Override
  public void close() throws Exception {
    closePreparedStatement();
    super.close();
  }

  protected void closePreparedStatement() {
    if (preparedStatement != null) {
      try {
        preparedStatement.close();
      } catch (Exception e) {
        handleErrorDuringClose(e);
      }
    }
  }

  protected void handleErrorDuringClose(Exception e) {}

  public PreparedStatement getPreparedStatement() {
    return (preparedStatement);
  }
}
