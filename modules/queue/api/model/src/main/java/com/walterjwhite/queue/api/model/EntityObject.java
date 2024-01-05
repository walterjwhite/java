package com.walterjwhite.queue.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.model.entity.EntityType;
import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

/**
 * Wrapper to store any POJO that does not inherit from AbstractEntity. Stores the Type and the hash
 * as the PK.
 */
@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
@NoArgsConstructor
@AllArgsConstructor
public class EntityObject extends AbstractEntity implements Unqueueable {

  protected EntityType entityType;

  /** SHA256 sum of the data * */
  protected String hash;

  @EqualsAndHashCode.Exclude protected byte[] serializedObject;
}
