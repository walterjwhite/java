package com.walterjwhite.shell.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
@NoArgsConstructor
public class Service extends AbstractNamedEntity {
  public Service(String name) {
    super(name);
  }
}
