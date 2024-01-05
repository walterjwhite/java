package com.walterjwhite.shell.api.model.traceroute;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
@AllArgsConstructor
@PersistenceCapable
public class TracrouteHopResponse extends AbstractEntity {

  protected int index;

  @EqualsAndHashCode.Exclude protected double responseTime; // in ms

  protected TracerouteHop tracerouteHop;
}
