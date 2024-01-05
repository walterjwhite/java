package com.walterjwhite.shell.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// @Data
@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@PersistenceCapable
@NoArgsConstructor
public class NetworkDiagnosticTest extends AbstractNamedEntity {

  protected String fqdn;

  public NetworkDiagnosticTest(String name, String fqdn) {
    super(name);
    this.fqdn = fqdn;
  }

  public NetworkDiagnosticTest(String name, String description, String fqdn) {
    super(name, description);
    this.fqdn = fqdn;
  }
}
