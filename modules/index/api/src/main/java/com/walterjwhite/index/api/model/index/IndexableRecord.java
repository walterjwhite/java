package com.walterjwhite.index.api.model.index;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.model.entity.Entity;
import com.walterjwhite.datastore.api.model.entity.EntityReference;
import lombok.*;

@AllArgsConstructor
// @RequiredArgsConstructor
@NoArgsConstructor
@Data
@ToString(doNotUseGetters = true)
public class IndexableRecord extends AbstractEntity {
  protected Index index;
  protected EntityReference entityReference;

  // object to be serialized via JSON-serialization service
  @ToString.Exclude protected transient Entity object;
  // object serialized via JSON-serialization service
  @ToString.Exclude protected transient byte[] data;
}
