package com.walterjwhite.data.pipe.api.source;

import com.walterjwhite.data.pipe.api.sink.Sink;

public abstract class AbstractSinkConfiguration {
  protected final Class<? extends Sink> sinkClass;

  public AbstractSinkConfiguration(Class<? extends Sink> sinkClass) {
    this.sinkClass = sinkClass;
  }

  public Class<? extends Sink> getSinkClass() {
    return sinkClass;
  }
}
