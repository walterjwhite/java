package com.walterjwhite.csv.api.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
public class CSVRecordPK implements Serializable {
  protected final String csvFilename;
  protected final int rowNumber;
}
