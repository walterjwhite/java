package com.walterjwhite.shell.impl.provider;

import com.walterjwhite.shell.impl.OutputCollectorConfiguration;
import jakarta.inject.Provider;

public class OutputCollectorConfigurationProvider
    implements Provider<OutputCollectorConfiguration> {
  protected final OutputCollectorConfiguration outputCollectorConfiguration;

  public OutputCollectorConfigurationProvider() {
    outputCollectorConfiguration = null;
  }

  @Override
  public OutputCollectorConfiguration get() {
    return outputCollectorConfiguration;
  }
}
