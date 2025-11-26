package com.walterjwhite.data.pipe.modules.jdbc.copy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
@AllArgsConstructor
public class TableCopy {
  protected String name;
  protected String description;
  protected DatabaseCopySession databaseCopySession;
}
