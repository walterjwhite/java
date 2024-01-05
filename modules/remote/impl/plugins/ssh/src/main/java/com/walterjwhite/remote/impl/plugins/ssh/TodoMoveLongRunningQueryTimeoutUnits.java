package com.walterjwhite.remote.impl.plugins.ssh;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import java.time.temporal.ChronoUnit;

public interface TodoMoveLongRunningQueryTimeoutUnits extends ConfigurableProperty {
  @DefaultValue ChronoUnit Default = ChronoUnit.MINUTES;
}
