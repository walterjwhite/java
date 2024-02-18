package com.walterjwhite.datastore.jdo.model;

import com.walterjwhite.datastore.api.model.EntityContainerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;


@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
@AllArgsConstructor
@PersistenceCapable
public class EntityType extends AbstractEntity {
  @Persistent
  protected String className;
  @Persistent
  protected EntityContainerType entityContainerType;
}
