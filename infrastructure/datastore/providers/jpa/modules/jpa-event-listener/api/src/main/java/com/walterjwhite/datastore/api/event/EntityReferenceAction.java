package com.walterjwhite.datastore.api.event;

import com.walterjwhite.datastore.api.event.enumeration.JPAActionType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//  /*This entity will be persisted in the same transaction as the reference
// entity, so we must be careful not to fire another event, otherwise, this will be an endless cycle
// ...*/
@PersistenceCapable
@NoArgsConstructor
@Data
@ToString(doNotUseGetters = true)
@Deprecated
public class EntityReferenceAction extends EntityReference {
  //

  public EntityReferenceAction(
      EntityType entityType,
      Integer entityId,
      JPAActionType jpaActionType,
      Transaction transaction) {
    super(entityType, entityId);
    this.jpaActionType = jpaActionType;
    this.transaction = transaction;
  }
}
