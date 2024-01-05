package com.walterjwhite.queue.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import java.util.HashSet;
import java.util.Set;
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
public class QueueMonitor extends AbstractNamedEntity implements Unqueueable {
  protected Set<Queue> queues = new HashSet<>();

  public QueueMonitor(String name, Set<Queue> queues) {
    this(name);
    this.queues.addAll(queues);
  }

  public QueueMonitor(String name) {
    super(name);
  }
}
