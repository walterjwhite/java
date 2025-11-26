package com.walterjwhite.datastore.jdo.model;

import com.walterjwhite.datastore.api.model.Entity;
import javax.jdo.annotations.*;
import lombok.Data;
import lombok.ToString;

@PersistenceCapable
@Data
@ToString(doNotUseGetters = true)
@Inheritance(strategy = InheritanceStrategy.COMPLETE_TABLE)
public abstract class AbstractEntity implements Entity<Integer> {
  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
  protected Integer id;
}
