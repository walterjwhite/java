package com.walterjwhite.shell.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import javax.jdo.annotations.PersistenceCapable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/** temporary class until the system configuration classes are moved in. */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class NetworkInterface extends AbstractEntity {

  protected Node node;

  protected String interfaceName;

  /** All of the digRequestIPAddresses assigned to this interface */
  //
  //
  //  protected Set<IPAddress> digRequestIPAddresses = new HashSet<>();
}
