package com.walterjwhite.serialization.modules.java;

import jakarta.inject.Inject;
import jakarta.inject.Provider;

public class KryoConfigurationProvider implements Provider<KryoConfiguration> {
  protected final KryoConfiguration kryoConfiguration = new KryoConfiguration();

  @Inject
  public KryoConfigurationProvider() {}


  @Override
  public KryoConfiguration get() {
    return kryoConfiguration;
  }
}
