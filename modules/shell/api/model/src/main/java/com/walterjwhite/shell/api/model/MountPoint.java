package com.walterjwhite.shell.api.model;


import com.walterjwhite.shell.api.enumeration.VFSType;
import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

@Data
@AllArgsConstructor
@ToString(doNotUseGetters = true)
@NoArgsConstructor
@PersistenceCapable
public class MountPoint /* implements Configurable*/ {

  protected String mountPoint;

  @EqualsAndHashCode.Exclude protected String device;

  @EqualsAndHashCode.Exclude protected VFSType vfsType;


  @EqualsAndHashCode.Exclude protected String options;

  public MountPoint(String mountPoint, String device, VFSType vfsType) {
    this(mountPoint, device, vfsType, "");
  }
}
