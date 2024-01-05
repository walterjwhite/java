package com.walterjwhite.ssh.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
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
public class SSHUser extends AbstractNamedEntity {
  protected String publicKey;

  protected Set<SSHHost> hosts = new HashSet<>();

  public SSHUser(String name) {
    super(name);
  }

  public SSHUser(String username, String publicKey, Set<SSHHost> hosts) {
    super(username);

    this.publicKey = publicKey;
    if (hosts != null && !hosts.isEmpty()) this.hosts.addAll(hosts);
  }
}
