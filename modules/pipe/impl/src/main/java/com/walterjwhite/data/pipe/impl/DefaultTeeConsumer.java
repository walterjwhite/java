package com.walterjwhite.data.pipe.impl;

import com.walterjwhite.data.pipe.api.sink.Sink;
import com.walterjwhite.data.pipe.api.source.AbstractSinkConfiguration;
import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class DefaultTeeConsumer implements Consumer, AutoCloseable {
  protected final Set<Sink> sinks = new HashSet<>();

  public DefaultTeeConsumer(final Set<AbstractSinkConfiguration> sinkConfigurations) {

    for (final AbstractSinkConfiguration sinkConfiguration : sinkConfigurations) {
      Sink sink =
          ApplicationHelper.getApplicationInstance()
              .getInjector()
              .getInstance(sinkConfiguration.getSinkClass());
      sink.configure(sinkConfiguration);
      sinks.add(sink);
    }
  }

  @Override
  public void accept(Object o) {
    for (Sink sink : sinks) sink.accept(o);
  }

  @Override
  public void close() throws Exception {
    for (Sink sink : sinks) sink.close();
  }
}
