package com.walterjwhite.data.pipe.modules.csv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString(doNotUseGetters = true)
public class CSVConfiguration {
  protected String filename;
  protected String[] headers;
}
