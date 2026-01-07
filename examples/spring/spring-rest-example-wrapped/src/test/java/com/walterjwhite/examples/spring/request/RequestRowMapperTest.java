package com.walterjwhite.examples.spring.request;

import com.walterjwhite.web.servlet.request.Request;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RequestRowMapperTest {

  @Test
  void mapsRow_withId() throws Exception {
    ResultSet rs = Mockito.mock(ResultSet.class);
    Mockito.when(rs.getLong("id")).thenReturn(123L);
    Mockito.when(rs.wasNull()).thenReturn(false);
    Mockito.when(rs.getString("ip")).thenReturn("127.0.0.1");
    Timestamp ts = Timestamp.from(Instant.parse("2025-01-01T00:00:00Z"));
    Mockito.when(rs.getTimestamp("timestamp")).thenReturn(ts);
    Mockito.when(rs.getString("uri")).thenReturn("/test");
    Mockito.when(rs.getString("user_agent")).thenReturn("curl/1.0");

    Request req = RequestRowMapper.INSTANCE.mapRow(rs, 0);

    Assertions.assertThat(req).isNotNull();
    Assertions.assertThat(req.getId()).isEqualTo(123L);
    Assertions.assertThat(req.getIp()).isEqualTo("127.0.0.1");
    Assertions.assertThat(req.getTimestamp()).isEqualTo(ts.toInstant());
    Assertions.assertThat(req.getUri()).isEqualTo("/test");
    Assertions.assertThat(req.getUserAgent()).isEqualTo("curl/1.0");
  }

  @Test
  void mapsRow_withoutIdColumn() throws Exception {
    ResultSet rs = Mockito.mock(ResultSet.class);
    Mockito.when(rs.getLong("id")).thenThrow(new SQLException("column not found"));
    Mockito.when(rs.getString("ip")).thenReturn("10.0.0.1");
    Timestamp ts = Timestamp.from(Instant.parse("2025-02-02T00:00:00Z"));
    Mockito.when(rs.getTimestamp("timestamp")).thenReturn(ts);
    Mockito.when(rs.getString("uri")).thenReturn("/noid");
    Mockito.when(rs.getString("user_agent")).thenReturn("agent");

    Request req = RequestRowMapper.INSTANCE.mapRow(rs, 0);

    Assertions.assertThat(req).isNotNull();
    Assertions.assertThat(req.getId()).isNull();
    Assertions.assertThat(req.getIp()).isEqualTo("10.0.0.1");
    Assertions.assertThat(req.getTimestamp()).isEqualTo(ts.toInstant());
    Assertions.assertThat(req.getUri()).isEqualTo("/noid");
    Assertions.assertThat(req.getUserAgent()).isEqualTo("agent");
  }
}
