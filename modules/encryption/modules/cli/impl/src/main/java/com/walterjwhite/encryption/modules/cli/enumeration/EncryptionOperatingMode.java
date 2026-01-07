package com.walterjwhite.encryption.modules.cli.enumeration;

import com.walterjwhite.encryption.modules.cli.handler.DecryptHandler;
import com.walterjwhite.encryption.modules.cli.handler.EncryptHandler;
import com.walterjwhite.encryption.modules.cli.handler.IVHandler;
import com.walterjwhite.encryption.modules.cli.handler.KeyHandler;
import com.walterjwhite.inject.cli.property.OperatingMode;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.property.api.annotation.DefaultValue;

public enum EncryptionOperatingMode implements OperatingMode {
  @DefaultValue
  Encrypt("Encrypt input text", EncryptHandler.class),
  Decrypt("Send a message(s)", DecryptHandler.class),
  Digest("One-way hash", DecryptHandler.class),
  Key("Generate encryption key", KeyHandler.class),
  IV("Generate encryption IV", IVHandler.class);

  private final String description;
  private final Class<? extends CommandLineHandler> initiatorClass;

  EncryptionOperatingMode(String description, Class<? extends CommandLineHandler> initiatorClass) {
    this.description = description;
    this.initiatorClass = initiatorClass;
  }

  @Override
  public Class<? extends CommandLineHandler> getInitiatorClass() {
    return initiatorClass;
  }
}
