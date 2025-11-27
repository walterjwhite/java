package com.walterjwhite.ssh.api.model;

import java.util.HashSet;
import java.util.Set;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
@PersistenceCapable
public class SSHUser {
  protected String username;
  protected String publicKey;

  protected Set<SSHHost> hosts = new HashSet<>();

  public SSHUser(String username) {
    this.username = username;
  }

  public SSHUser(String username, String publicKey, Set<SSHHost> hosts) {
    this.username = username;
    this.publicKey = publicKey;
    if (hosts != null && !hosts.isEmpty()) {
      this.hosts.addAll(hosts);
    }
  }
}
