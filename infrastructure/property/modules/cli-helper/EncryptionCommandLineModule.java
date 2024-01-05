package com.walterjwhite.property.modules.cli;

import com.walterjwhite.google.guice.cli.AbstractCommandLineModule;
import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.property.modules.cli.enumeration.PropertyEncryptionOperatingMode;
import org.reflections.Reflections;

public class EncryptionCommandLineModule extends AbstractCommandLineModule {

  public EncryptionCommandLineModule(PropertyManager propertyManager, Reflections reflections) {
    super(propertyManager, reflections, PropertyEncryptionOperatingMode.class);
  }

  /**
   * FYI, at this point, properties should be injected, so we can do a dynamic configuration here
   * ...
   */
  @Override
  protected void doCliConfigure() {}
}
