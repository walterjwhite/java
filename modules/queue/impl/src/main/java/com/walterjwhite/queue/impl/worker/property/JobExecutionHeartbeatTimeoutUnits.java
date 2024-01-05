package com.walterjwhite.queue.impl.worker.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import java.time.temporal.ChronoUnit;

public interface JobExecutionHeartbeatTimeoutUnits extends ConfigurableProperty {
  @DefaultValue ChronoUnit Default = ChronoUnit.MINUTES;
}
