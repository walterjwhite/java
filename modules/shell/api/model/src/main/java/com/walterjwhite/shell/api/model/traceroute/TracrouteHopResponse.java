package com.walterjwhite.shell.api.model.traceroute;

import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
@AllArgsConstructor
@PersistenceCapable
public class TracrouteHopResponse {

  protected int index;

  @EqualsAndHashCode.Exclude protected double responseTime; // in ms

  protected TracerouteHop tracerouteHop;
}
