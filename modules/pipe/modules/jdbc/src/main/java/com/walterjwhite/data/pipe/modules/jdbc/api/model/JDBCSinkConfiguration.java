package com.walterjwhite.data.pipe.modules.jdbc.api.model;

import com.walterjwhite.data.pipe.api.source.AbstractSinkConfiguration;
import com.walterjwhite.data.pipe.modules.jdbc.JDBCSink;
import com.walterjwhite.data.pipe.modules.jdbc.copy.JDBCQueryConfiguration;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class JDBCSinkConfiguration extends AbstractSinkConfiguration {
  protected JDBCQueryConfiguration jdbcQueryConfiguration;

  protected int recordsPerUnitOfWork = -1;
  protected boolean createTables = false;

  public JDBCSinkConfiguration() {
    super(JDBCSink.class);
  }
}
