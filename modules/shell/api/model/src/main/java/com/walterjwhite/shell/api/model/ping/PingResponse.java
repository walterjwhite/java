package com.walterjwhite.shell.api.model.ping;

import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
@AllArgsConstructor
@PersistenceCapable
public class PingResponse {

  protected PingRequest pingRequest;

  protected int index;

  @EqualsAndHashCode.Exclude protected int ttl;

  @EqualsAndHashCode.Exclude protected double time;
}
