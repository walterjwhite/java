package com.walterjwhite.shell.api.model.traceroute;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.shell.api.model.NetworkDiagnosticTest;
import com.walterjwhite.shell.api.model.ShellCommand;
import com.walterjwhite.shell.api.model.ShellCommandable;
import java.time.LocalDateTime;
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
public class TracerouteRequest extends AbstractEntity implements ShellCommandable {

  protected NetworkDiagnosticTest networkDiagnosticTest;

  protected LocalDateTime dateTime = LocalDateTime.now();

  @EqualsAndHashCode.Exclude protected ShellCommand shellCommand;

  @EqualsAndHashCode.Exclude protected boolean ipv4 = true;

  @EqualsAndHashCode.Exclude protected int queriesPerHop = -1;

  @EqualsAndHashCode.Exclude protected int maxHops = -1;

  @EqualsAndHashCode.Exclude protected boolean noFragment;
  @EqualsAndHashCode.Exclude protected int timeout;

  protected List<TracerouteHop> tracerouteHops = new ArrayList<>();

  public TracerouteRequest(NetworkDiagnosticTest networkDiagnosticTest) {

    this.networkDiagnosticTest = networkDiagnosticTest;
  }
}
