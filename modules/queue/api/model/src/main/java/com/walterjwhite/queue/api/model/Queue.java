package com.walterjwhite.queue.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import com.walterjwhite.queue.api.enumeration.QueueType;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@PersistenceCapable
@NoArgsConstructor
public class Queue extends AbstractNamedEntity implements Unqueueable {

  protected QueueType queueType;

  public Queue(String name, QueueType queueType) {
    super(name);
    this.queueType = queueType;
  }
}
