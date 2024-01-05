package com.walterjwhite.shell.api.model.ping;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
@AllArgsConstructor
@PersistenceCapable
public class PingResponse extends AbstractEntity {

  protected PingRequest pingRequest;

  protected int index;

  @EqualsAndHashCode.Exclude protected int ttl;

  @EqualsAndHashCode.Exclude protected double time;
}
