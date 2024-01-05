package com.walterjwhite.data.pipe.api.sink;

import com.walterjwhite.data.pipe.api.source.Source;

public abstract class AbstractSourceConfiguration {
  protected final Class<? extends Source> sourceClass;

  public AbstractSourceConfiguration(Class<? extends Source> sourceClass) {
    this.sourceClass = sourceClass;
  }

  public Class<? extends Source> getSourceClass() {
    return sourceClass;
  }
}
