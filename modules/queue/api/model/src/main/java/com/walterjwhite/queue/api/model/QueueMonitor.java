package com.walterjwhite.queue.api.model;

import java.util.HashSet;
import java.util.Set;
import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@PersistenceCapable
@NoArgsConstructor
@AllArgsConstructor
public class QueueMonitor implements Unqueueable {
  protected Set<Queue> queues = new HashSet<>();

}
