package com.walterjwhite.data.pipe.modules.jdbc.copy;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
public class TableCopy extends AbstractNamedEntity {
  protected DatabaseCopySession databaseCopySession;

  public TableCopy(String name, String description, DatabaseCopySession databaseCopySession) {
    super(name, description);
    this.databaseCopySession = databaseCopySession;
  }
}
