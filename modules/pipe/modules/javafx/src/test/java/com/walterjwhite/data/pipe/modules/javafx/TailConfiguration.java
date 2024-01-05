package com.walterjwhite.data.pipe.modules.javafx;

import com.walterjwhite.data.pipe.api.sink.AbstractSourceConfiguration;

public class TailConfiguration extends AbstractSourceConfiguration {
  public TailConfiguration() {
    super(TailSource.class);
  }
}
