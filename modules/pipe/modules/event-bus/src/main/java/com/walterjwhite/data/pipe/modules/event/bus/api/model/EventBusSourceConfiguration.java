package com.walterjwhite.data.pipe.modules.event.bus.api.model;

import com.walterjwhite.data.pipe.api.sink.AbstractSourceConfiguration;
import com.walterjwhite.data.pipe.modules.event.bus.EventBusSource;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class EventBusSourceConfiguration extends AbstractSourceConfiguration {
  protected EventBusConfiguration eventBusConfiguration;

  public EventBusSourceConfiguration() {
    super(EventBusSource.class);
  }
}
