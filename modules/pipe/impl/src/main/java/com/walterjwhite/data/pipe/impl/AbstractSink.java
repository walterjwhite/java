package com.walterjwhite.data.pipe.impl;

import com.walterjwhite.data.pipe.api.sink.Sink;
import com.walterjwhite.data.pipe.api.source.AbstractSinkConfiguration;
import java.io.Serializable;

public abstract class AbstractSink<
        RecordType extends Serializable, SinkConfigurationType extends AbstractSinkConfiguration>
    implements Sink<RecordType, SinkConfigurationType> {
  protected SinkConfigurationType sinkConfiguration;

  public void configure(SinkConfigurationType sinkConfiguration) {
    this.sinkConfiguration = sinkConfiguration;
    doConfigure();
  }

  protected abstract void doConfigure();
}
