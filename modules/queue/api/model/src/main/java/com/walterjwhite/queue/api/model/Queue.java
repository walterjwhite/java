package com.walterjwhite.queue.api.model;

import com.walterjwhite.queue.api.enumeration.QueueType;
import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@PersistenceCapable
@NoArgsConstructor
@AllArgsConstructor
public class Queue implements Unqueueable {
  protected String name;
  protected QueueType queueType;

}
