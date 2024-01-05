package com.walterjwhite.shell.api.model.traceroute;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.shell.api.model.IPAddress;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
@PersistenceCapable
public class TracerouteHop extends AbstractEntity {

  protected TracerouteRequest tracerouteRequest;

  protected int index;

  @EqualsAndHashCode.Exclude protected String fqdn;

  @EqualsAndHashCode.Exclude protected IPAddress ipAddress;

  protected List<TracrouteHopResponse> tracrouteHopResponses = new ArrayList<>();

  public TracerouteHop(
      TracerouteRequest tracerouteRequest, int index, String fqdn, IPAddress ipAddress) {

    this.tracerouteRequest = tracerouteRequest;
    this.index = index;
    this.fqdn = fqdn;
    this.ipAddress = ipAddress;
  }
}
