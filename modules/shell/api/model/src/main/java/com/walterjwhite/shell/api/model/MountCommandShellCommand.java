package com.walterjwhite.shell.api.model;

import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class MountCommandShellCommand {
  protected MountCommand mountCommand;

  protected ShellCommand shellCommand;

  public MountCommandShellCommand withShellCommand(ShellCommand shellCommand) {
    this.shellCommand = shellCommand;
    return this;
  }

  public MountCommandShellCommand withMountCommand(MountCommand mountCommand) {
    this.mountCommand = mountCommand;
    return this;
  }
}
