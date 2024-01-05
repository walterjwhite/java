package com.walterjwhite.data.pipe.modules.jdbc.copy;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
public class DatabaseCopySession extends AbstractNamedEntity {

  protected JDBCQueryConfiguration sourceConfiguration;
  protected JDBCQueryConfiguration targetConfiguration;

  protected boolean nop;

  protected LocalDateTime startDateTime;
  protected LocalDateTime endDateTime;

  protected List<String> tableNames = new ArrayList<>();

  public DatabaseCopySession(
      String name,
      String description,
      JDBCQueryConfiguration sourceConfiguration,
      JDBCQueryConfiguration targetConfiguration,
      boolean nop,
      LocalDateTime startDateTime,
      LocalDateTime endDateTime) {
    super(name, description);
    this.sourceConfiguration = sourceConfiguration;
    this.targetConfiguration = targetConfiguration;
    this.nop = nop;
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
  }
}
