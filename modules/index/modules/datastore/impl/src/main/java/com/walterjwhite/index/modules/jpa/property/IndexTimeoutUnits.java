package com.walterjwhite.index.modules.jpa.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import java.time.temporal.ChronoUnit;

public interface IndexTimeoutUnits extends /*TimeoutUnits*/ ConfigurableProperty {
  @DefaultValue ChronoUnit Default = ChronoUnit.MILLIS;
}
