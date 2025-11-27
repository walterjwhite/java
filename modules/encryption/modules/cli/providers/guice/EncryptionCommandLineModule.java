package com.walterjwhite.encryption.modules.cli.module;

import com.walterjwhite.encryption.impl.EncryptionModule;
import com.walterjwhite.encryption.modules.cli.enumeration.EncryptionOperatingMode;
import com.walterjwhite.google.guice.cli.AbstractCommandLineModule;
import com.walterjwhite.property.api.PropertyManager;
import org.reflections.Reflections;

public class EncryptionCommandLineModule extends AbstractCommandLineModule {

  public EncryptionCommandLineModule(PropertyManager propertyManager, Reflections reflections) {
    super(propertyManager, reflections, EncryptionOperatingMode.class);
  }

  @Override
  protected void doCliConfigure() {
    install(new EncryptionModule());
  }
}
