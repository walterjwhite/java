package com.walterjwhite.examples.spring.request;

import com.walterjwhite.web.servlet.request.Request;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import org.springframework.jdbc.core.RowMapper;

public class RequestRowMapper implements RowMapper<Request> {

  public static final RequestRowMapper INSTANCE = new RequestRowMapper();

  @Override
  public Request mapRow(ResultSet rs, int rowNum) throws SQLException {
    Long id = null;
    try {
      long tmp = rs.getLong("id");
      if (!rs.wasNull()) {
        id = tmp;
      }
    } catch (SQLException e) {
    }

    final String ip = rs.getString("ip");
    final Timestamp ts = rs.getTimestamp("timestamp");
    final Instant instant = ts == null ? null : ts.toInstant();
    final String uri = rs.getString("uri");
    final String ua = rs.getString("user_agent");

    return new Request(id, ip, instant, uri, ua);
  }
}
