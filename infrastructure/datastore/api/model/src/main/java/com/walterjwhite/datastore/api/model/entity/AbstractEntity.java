package com.walterjwhite.datastore.api.model.entity;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceAware;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import lombok.Data;
import lombok.ToString;

@PersistenceAware
@Data
@ToString(doNotUseGetters = true)
public abstract class AbstractEntity implements Entity<Integer> {
  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
  protected Integer id;
}
