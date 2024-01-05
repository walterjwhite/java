package com.walterjwhite.datastore.api.model.entity;

import java.io.Serializable;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@AllArgsConstructor
@NoArgsConstructor
@PersistenceCapable
@Deprecated
/**
 * This is a bridge entity - ie. it stores a reference to another data store such as ES, redis, etc.
 */
public class EntityReference extends AbstractEntity {
  // a composite primary key requires a primary key id class to be defined :(
  @PrimaryKey protected EntityType entityType;

  @PrimaryKey protected Serializable entityId;
}
