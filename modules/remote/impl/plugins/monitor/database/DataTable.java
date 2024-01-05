package com.walterjwhite.remote.impl.service.database;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.ToString;

/** TODO: support more complex data types rather than just strings. */
@Data
@ToString(doNotUseGetters = true)
public class DataTable {

  protected List<String> columnNames;

  protected final List<List<String>> rows = new ArrayList<List<String>>();

  public void clear() {
    rows.clear();
  }

  public void add(List<String> row) {
    this.rows.add(row);
  }

  /*
      public String[][] get() {
          if (rows == null || rows.isEmpty()) {
              return (new String[][]{});
          }

          final String[][] results = new String[rows.size()][columnNames.length];

          for (int i = 0; i < rows.size(); i++) {
              for (int j = 0; j < columnNames.length; j++) {
                  results[i][j] = rows.get(i).get(j);
              }
          }

          return (results);
      }
  */
}
