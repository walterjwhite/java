package com.walterjwhite.datastore.jpa.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@AllArgsConstructor
@NoArgsConstructor
public class EntityReference extends AbstractEntity {
  protected EntityType entityType;

  protected Serializable entityId;
}
