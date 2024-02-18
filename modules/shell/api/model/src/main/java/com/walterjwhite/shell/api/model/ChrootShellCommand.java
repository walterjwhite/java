package com.walterjwhite.shell.api.model;

import javax.jdo.annotations.PersistenceCapable;
import lombok.*;




@ToString(doNotUseGetters = true)
@Getter
@Setter
@NoArgsConstructor
@PersistenceCapable
public class ChrootShellCommand extends ShellCommand implements Chrootable {
  protected String chrootPath;

  public ChrootShellCommand withChrootPath(final String chrootPath) {
    this.chrootPath = chrootPath;
    return this;
  }
}
