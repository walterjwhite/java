package com.walterjwhite.datastore.jdo.model;

import com.walterjwhite.datastore.api.model.Entity;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import lombok.Data;
import lombok.ToString;

@PersistenceCapable
@Data
@ToString(doNotUseGetters = true)
@Inheritance(strategy = InheritanceStrategy.COMPLETE_TABLE)
public abstract class AbstractUUIDEntity implements Entity<String> {
  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.UUIDHEX)
  protected String id;
}
