package com.walterjwhite.datastore.api.event;

import com.walterjwhite.datastore.api.model.entity.AbstractUUIDEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@NoArgsConstructor
@PersistenceCapable
@Deprecated
public class Transaction extends AbstractUUIDEntity {
  protected transient EntityTransaction entityTransaction;

  public Transaction(EntityTransaction entityTransaction) {
    this.entityTransaction = entityTransaction;
  }
}
