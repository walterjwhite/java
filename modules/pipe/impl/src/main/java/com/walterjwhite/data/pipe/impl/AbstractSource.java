package com.walterjwhite.data.pipe.impl;

import com.walterjwhite.data.pipe.api.sink.AbstractSourceConfiguration;
import com.walterjwhite.data.pipe.api.source.Source;
import java.io.Serializable;

public abstract class AbstractSource<
        RecordType extends Serializable,
        SourceConfigurationType extends AbstractSourceConfiguration>
    implements Source<RecordType, SourceConfigurationType> {
  protected SourceConfigurationType sourceConfiguration;

  public void configure(SourceConfigurationType sourceConfiguration) {
    this.sourceConfiguration = sourceConfiguration;
    doConfigure();
  }

  protected abstract void doConfigure();
}
