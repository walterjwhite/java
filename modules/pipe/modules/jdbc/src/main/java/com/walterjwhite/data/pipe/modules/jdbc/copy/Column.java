package com.walterjwhite.data.pipe.modules.jdbc.copy;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import java.sql.JDBCType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
public class Column extends AbstractEntity {
  protected String name;
  @EqualsAndHashCode.Exclude protected JDBCType type;
  @EqualsAndHashCode.Exclude protected int size;
  @EqualsAndHashCode.Exclude protected int decimalDigits;
  @EqualsAndHashCode.Exclude protected boolean nullable;
  @EqualsAndHashCode.Exclude protected boolean autoIncrement;

  public Column(
      String name,
      JDBCType type,
      int size,
      int decimalDigits,
      boolean nullable,
      boolean autoIncrement) {

    this.name = name;
    this.type = type;
    this.size = size;
    this.decimalDigits = decimalDigits;
    this.nullable = nullable;
    this.autoIncrement = autoIncrement;
  }
}
