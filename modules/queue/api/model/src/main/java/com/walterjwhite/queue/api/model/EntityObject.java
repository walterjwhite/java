package com.walterjwhite.queue.api.model;

import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
@NoArgsConstructor
@AllArgsConstructor
public class EntityObject implements Unqueueable {


  protected String hash;

  @EqualsAndHashCode.Exclude protected byte[] serializedObject;
}
