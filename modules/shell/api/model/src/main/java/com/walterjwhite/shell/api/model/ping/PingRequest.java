package com.walterjwhite.shell.api.model.ping;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.shell.api.enumeration.PingResponseType;
import com.walterjwhite.shell.api.model.IPAddress;
import com.walterjwhite.shell.api.model.NetworkDiagnosticTest;
import com.walterjwhite.shell.api.model.ShellCommand;
import com.walterjwhite.shell.api.model.ShellCommandable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/** Instructs a client to ping a target. */
@PersistenceCapable
@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
public class PingRequest extends AbstractEntity implements ShellCommandable {
  protected NetworkDiagnosticTest networkDiagnosticTest;

  protected LocalDateTime dateTime = LocalDateTime.now();

  // -c
  @EqualsAndHashCode.Exclude protected int count = 10;

  // -W
  @EqualsAndHashCode.Exclude protected int timeout = 5;

  // -i
  @EqualsAndHashCode.Exclude protected int interval = 1;

  @EqualsAndHashCode.Exclude protected IPAddress ipAddress;

  @EqualsAndHashCode.Exclude protected PingResponseType pingResponseType;

  @EqualsAndHashCode.Exclude protected List<PingResponse> pingResponses = new ArrayList<>();

  @EqualsAndHashCode.Exclude protected ShellCommand shellCommand;

  public PingRequest(final NetworkDiagnosticTest networkDiagnosticTest) {

    this.networkDiagnosticTest = networkDiagnosticTest;
    this.count = new Random().nextInt(10);
  }
}
