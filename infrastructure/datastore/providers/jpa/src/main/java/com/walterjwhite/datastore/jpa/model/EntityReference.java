package com.walterjwhite.datastore.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;


@Data
@ToString(doNotUseGetters = true)
@AllArgsConstructor
@NoArgsConstructor
public class EntityReference extends AbstractEntity {
  protected EntityType entityType;

  protected Serializable entityId;
}
