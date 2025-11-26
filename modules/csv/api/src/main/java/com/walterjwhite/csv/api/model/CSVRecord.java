package com.walterjwhite.csv.api.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
public class CSVRecord {
  protected final CSVRecordPK id;

  @EqualsAndHashCode.Exclude protected final String[] columNames;

  @EqualsAndHashCode.Exclude protected final String[] data;
}
