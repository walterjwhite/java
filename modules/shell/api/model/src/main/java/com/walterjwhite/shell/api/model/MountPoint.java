package com.walterjwhite.shell.api.model;

// import com.walterjwhite.linux.builder.api.model.configuration.Configurable;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.shell.api.enumeration.VFSType;
import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

@Data
@AllArgsConstructor
@ToString(doNotUseGetters = true)
@NoArgsConstructor
@PersistenceCapable
public class MountPoint /* implements Configurable*/ extends AbstractEntity {

  protected String mountPoint;

  @EqualsAndHashCode.Exclude protected String device;

  @EqualsAndHashCode.Exclude protected VFSType vfsType;

  // protected String vfsType;

  @EqualsAndHashCode.Exclude protected String options;

  public MountPoint(String mountPoint, String device, VFSType vfsType) {
    this(mountPoint, device, vfsType, "");
  }
}
