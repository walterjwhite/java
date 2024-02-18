package com.walterjwhite.remote.impl.service.database;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.ToString;


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

  
}
