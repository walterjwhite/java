package com.walterjwhite.property.modules.cli.enumeration;

import com.walterjwhite.inject.cli.property.OperatingMode;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.modules.cli.handler.GetSecretPropertyHandler;
import com.walterjwhite.property.modules.cli.handler.PutSecretPropertyHandler;

public enum PropertyEncryptionOperatingMode implements OperatingMode {
  @DefaultValue
  Encrypt("Encrypt input text", PutSecretPropertyHandler.class),
  Decrypt("Send a message(s)", GetSecretPropertyHandler.class);

  private final String description;
  private final Class<? extends CommandLineHandler> initiatorClass;

  PropertyEncryptionOperatingMode(
      String description, Class<? extends CommandLineHandler> initiatorClass) {
    this.description = description;
    this.initiatorClass = initiatorClass;
  }

  @Override
  public Class<? extends CommandLineHandler> getInitiatorClass() {
    return initiatorClass;
  }
}
