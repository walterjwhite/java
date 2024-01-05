package com.walterjwhite.modules.web.service.sample;

import com.walterjwhite.modules.web.service.core.model.ServletMappingConfiguration;
import jakarta.inject.Provider;

public class ServletMappingConfigurationProvider implements Provider<ServletMappingConfiguration> {
  protected final ServletMappingConfiguration servletMappingConfiguration;

  public ServletMappingConfigurationProvider() {
    this.servletMappingConfiguration = new ServletMappingConfiguration();
  }

  @Override
  public ServletMappingConfiguration get() {
    return servletMappingConfiguration;
  }
}
