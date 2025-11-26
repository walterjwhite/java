package com.walterjwhite.shell.api.model;

import com.walterjwhite.shell.api.enumeration.MountAction;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

@ToString(doNotUseGetters = true) // (callSuper = true)
@Getter
@Setter
@PersistenceCapable
public class MountCommand /*extends AbstractUUIDEntity*/ implements MultipleShellCommandable {

  protected String rootPath;

  protected MountPoint mountPoint;

  @ToString.Exclude protected List<MountCommandShellCommand> shellCommands = new ArrayList<>();

  protected MountAction mountAction;

  protected int timeout;

  public void addShellCommand(ShellCommand shellCommand) {
    shellCommands.add(
        new MountCommandShellCommand().withShellCommand(shellCommand).withMountCommand(this));
  }

  public MountCommand withMountPoint(MountPoint mountPoint) {
    this.mountPoint = mountPoint;
    return this;
  }

  public MountCommand withMountAction(MountAction mountAction) {
    this.mountAction = mountAction;
    return this;
  }

  public MountCommand withRootPath(final String rootPath) {
    this.rootPath = rootPath;
    return this;
  }
}
