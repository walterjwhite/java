package com.walterjwhite.remote.modules.cli.enumeration;

import com.walterjwhite.inject.cli.property.OperatingMode;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.remote.modules.cli.handler.ListNodes;
import com.walterjwhite.remote.modules.cli.handler.MessageReader;
import com.walterjwhite.remote.modules.cli.handler.MessageWriter;
import com.walterjwhite.remote.modules.cli.handler.RemoteDaemon;

// I would like to automatically start any services that are needed
public enum RemoteOperatingMode implements OperatingMode {
  @DefaultValue
  Daemon("Run in the background as a service (default)", RemoteDaemon.class),
  Send("Send a message(s)", MessageWriter.class),
  Read("Read message(s)", MessageReader.class),
  List("List online nodes and statuses", ListNodes.class);

  private final String description;
  private final Class<? extends CommandLineHandler> initiatorClass;

  RemoteOperatingMode(String description, Class<? extends CommandLineHandler> initiatorClass) {
    this.description = description;
    this.initiatorClass = initiatorClass;
  }

  @Override
  public Class<? extends CommandLineHandler> getInitiatorClass() {
    return initiatorClass;
  }
}
