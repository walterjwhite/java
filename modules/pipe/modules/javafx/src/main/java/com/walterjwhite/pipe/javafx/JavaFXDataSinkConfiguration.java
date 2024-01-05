package com.walterjwhite.pipe.javafx;

import com.walterjwhite.data.pipe.api.source.AbstractSinkConfiguration;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class JavaFXDataSinkConfiguration extends AbstractSinkConfiguration {
  protected int maxRows;
  protected String dataClassName;

  protected String title;
  protected String description;
  protected String[] columnNames;

  public JavaFXDataSinkConfiguration(
      int maxRows,
      String dataClassName,
      String gridColumnResolverClassName,
      String title,
      String description,
      final String... columnNames) {
    this();
    this.maxRows = maxRows;
    this.dataClassName = dataClassName;
    this.columnNames = columnNames;
    this.title = title;
    this.description = description;
  }

  public JavaFXDataSinkConfiguration() {
    super(JavaFXDataSink.class);
  }
}
