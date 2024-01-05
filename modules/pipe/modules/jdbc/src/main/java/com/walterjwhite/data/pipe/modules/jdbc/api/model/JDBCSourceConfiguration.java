package com.walterjwhite.data.pipe.modules.jdbc.api.model;

import com.walterjwhite.data.pipe.api.sink.AbstractSourceConfiguration;
import com.walterjwhite.data.pipe.modules.jdbc.JDBCSource;
import com.walterjwhite.data.pipe.modules.jdbc.copy.JDBCQueryConfiguration;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class JDBCSourceConfiguration extends AbstractSourceConfiguration {
  protected JDBCQueryConfiguration jdbcQueryConfiguration;

  public JDBCSourceConfiguration() {
    super(JDBCSource.class);
  }
}
