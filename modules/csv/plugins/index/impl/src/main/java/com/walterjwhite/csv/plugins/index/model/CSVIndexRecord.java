package com.walterjwhite.csv.plugins.index.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/** The "Entity" that gets pushed into the indexing service provider (ElasticSearch) */
@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
public class CSVIndexRecord implements Serializable {
  // CSV filename
  protected String name;
  protected Map<String, String> values = new HashMap<>();

  public CSVIndexRecord(String name, Map<String, String> values) {

    this.name = name;

    this.values.putAll(values);
  }
}
