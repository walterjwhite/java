package com.walterjwhite.data.pipe.impl.periodic;

import com.walterjwhite.data.pipe.api.sink.AbstractSourceConfiguration;
import java.util.concurrent.TimeUnit;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class PeriodicSourceConfiguration extends AbstractSourceConfiguration {
  protected long interval;
  protected TimeUnit timeUnit;
  protected long shutdownTimeout = 5;
  protected AbstractSourceConfiguration sourceConfiguration;

  public PeriodicSourceConfiguration(
      long interval,
      TimeUnit timeUnit,
      long shutdownTimeout,
      AbstractSourceConfiguration sourceConfiguration) {
    this();
    this.interval = interval;
    this.timeUnit = timeUnit;
    this.shutdownTimeout = shutdownTimeout;
    this.sourceConfiguration = sourceConfiguration;
  }

  public PeriodicSourceConfiguration() {
    super(PeriodicSource.class);
  }
}
