package com.walterjwhite.datastore.api.model.entity;

import javax.jdo.annotations.PersistenceAware;
import javax.jdo.annotations.PrimaryKey;
import lombok.*;

/** Base class to be used for named entities. */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(doNotUseGetters = true)
@PersistenceAware
public abstract class AbstractNamedEntity implements Entity<String> {

  /** Name of the entity, to be short, a quick reference. */
  @PrimaryKey protected String name;

  /** description of the entity, longer, more meaningful. */
  @EqualsAndHashCode.Exclude protected String description;

  protected AbstractNamedEntity(String name) {
    this.name = name;
  }

  @Override
  public String getId() {
    return name;
  }
}
