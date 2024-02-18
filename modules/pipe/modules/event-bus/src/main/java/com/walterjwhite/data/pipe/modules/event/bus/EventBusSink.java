package com.walterjwhite.data.pipe.modules.event.bus;

import com.google.common.eventbus.EventBus;
import com.walterjwhite.data.pipe.impl.AbstractSink;
import com.walterjwhite.data.pipe.modules.event.bus.api.model.EventBusSinkConfiguration;
import jakarta.inject.Inject;
import java.io.Serializable;

public class EventBusSink extends AbstractSink<Serializable, EventBusSinkConfiguration> {
  protected final EventBus eventBus;

  @Inject
  public EventBusSink(EventBus eventBus) {

    this.eventBus = eventBus;
  }

  @Override
  protected void doConfigure() {}

  @Override
  public void accept(Serializable record) {
    eventBus.post(record);
  }

  public void close() {}
}
