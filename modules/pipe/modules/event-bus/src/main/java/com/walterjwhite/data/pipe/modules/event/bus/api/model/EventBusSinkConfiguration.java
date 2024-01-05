package com.walterjwhite.data.pipe.modules.event.bus.api.model;

import com.walterjwhite.data.pipe.api.source.AbstractSinkConfiguration;
import com.walterjwhite.data.pipe.modules.event.bus.EventBusSink;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class EventBusSinkConfiguration extends AbstractSinkConfiguration {
  protected EventBusConfiguration eventBusConfiguration;

  public EventBusSinkConfiguration() {
    super(EventBusSink.class);
  }
}
