package com.walterjwhite.examples.spring.request;

import com.walterjwhite.web.servlet.request.Request;
import com.walterjwhite.web.servlet.request.RequestConsumer;
import java.sql.Timestamp;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class RequestRepository implements RequestConsumer {
  private final JdbcTemplate jdbc;

  @Override
  public void onRequest(final Request request) {
    final var sql = "INSERT INTO request (ip, timestamp, uri, user_agent) VALUES (?, ?, ?, ?)";
    try {
      jdbc.update(
          sql,
          request.getIp(),
          Timestamp.from(request.getTimestamp()),
          request.getUri(),
          request.getUserAgent());
    } catch (Exception ex) {
      LOGGER.error("Failed to persist traffic log: ", ex);
    }
  }

  public List<Request> findAll() {
    final String sql = "SELECT ip, timestamp, uri, user_agent FROM request ORDER BY timestamp ASC";
    return jdbc.query(sql, RequestRowMapper.INSTANCE);
  }

  @Transactional
  public void truncate() {
    try {
      jdbc.execute("TRUNCATE TABLE request");
    } catch (Exception e) {
      LOGGER.warn("TRUNCATE failed, falling back to DELETE: {}", e.toString());
      jdbc.update("DELETE FROM request");
    }
  }
}
