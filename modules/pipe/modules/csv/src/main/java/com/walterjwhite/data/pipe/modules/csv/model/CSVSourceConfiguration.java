package com.walterjwhite.data.pipe.modules.csv.model;

import com.walterjwhite.data.pipe.api.sink.AbstractSourceConfiguration;
import com.walterjwhite.data.pipe.modules.csv.service.CSVSource;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class CSVSourceConfiguration extends AbstractSourceConfiguration {
  protected CSVConfiguration csvConfiguration;

  public CSVSourceConfiguration(CSVConfiguration csvConfiguration) {
    super(CSVSource.class);
    this.csvConfiguration = csvConfiguration;
  }
}
