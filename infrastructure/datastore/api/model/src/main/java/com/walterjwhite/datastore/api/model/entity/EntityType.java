package com.walterjwhite.datastore.api.model.entity;

import javax.jdo.annotations.PersistenceCapable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Entity, or in the case of a CSV, the filename */
@Deprecated
@Getter
@Setter
@NoArgsConstructor
@PersistenceCapable
public class EntityType extends AbstractNamedEntity {

  protected EntityContainerType entityContainerType;

  public EntityType(String name, EntityContainerType entityContainerType) {
    super(name);
    this.entityContainerType = entityContainerType;
  }
}
