package com.walterjwhite.data.pipe.modules.jdbc.copy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseCopySession {

  protected JDBCQueryConfiguration sourceConfiguration;
  protected JDBCQueryConfiguration targetConfiguration;

  protected boolean nop;

  protected LocalDateTime startDateTime;
  protected LocalDateTime endDateTime;

  protected List<String> tableNames = new ArrayList<>();
}
