package com.walterjwhite.ssh.api.model;

import javax.jdo.annotations.PersistenceAware;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@AllArgsConstructor
@NoArgsConstructor
@PersistenceAware
public abstract class AbstractSSHEntity {
  protected SSHHost host;

  protected SSHUser user;
}
