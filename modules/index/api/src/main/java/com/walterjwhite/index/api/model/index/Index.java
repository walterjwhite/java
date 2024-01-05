package com.walterjwhite.index.api.model.index;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import com.walterjwhite.datastore.api.model.entity.EntityType;
import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

/** 2 types: 1. Entity 2. CSV file */
@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@PersistenceCapable
@NoArgsConstructor
public class Index extends AbstractNamedEntity {
  @EqualsAndHashCode.Exclude
  //  protected final String entityType;

  protected EntityType entityType;

  @EqualsAndHashCode.Exclude protected String mapping;

  public Index(String name) {
    super(name);
  }
}
