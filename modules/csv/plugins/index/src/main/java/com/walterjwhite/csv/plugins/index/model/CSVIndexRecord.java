package com.walterjwhite.csv.plugins.index.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
public class CSVIndexRecord implements Serializable {
  protected String name;
  protected Map<String, String> values = new HashMap<>();

  public CSVIndexRecord(String name, Map<String, String> values) {

    this.name = name;

    this.values.putAll(values);
  }
}
