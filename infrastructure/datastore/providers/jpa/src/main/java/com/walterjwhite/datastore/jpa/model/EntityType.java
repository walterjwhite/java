package com.walterjwhite.datastore.jpa.model;

import com.walterjwhite.datastore.api.model.EntityContainerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
@AllArgsConstructor
public class EntityType extends AbstractEntity {
  protected String className;

  protected EntityContainerType entityContainerType;
}
