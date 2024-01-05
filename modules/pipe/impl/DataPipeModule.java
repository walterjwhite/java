package com.walterjwhite.data.pipe.impl;

import com.google.inject.AbstractModule;
import com.walterjwhite.data.pipe.api.Pipe;

public class DataPipeModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(Pipe.class).to(DefaultPipe.class);
  }
}
