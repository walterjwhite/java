package com.walterjwhite.shell.api.model.dig;

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

@PersistenceCapable
@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
public class DigRequest extends AbstractEntity implements ShellCommandable {
  protected NetworkDiagnosticTest networkDiagnosticTest;

  protected LocalDateTime requestDateTime = LocalDateTime.now();

  @EqualsAndHashCode.Exclude protected int timeout;

  @EqualsAndHashCode.Exclude protected ShellCommand shellCommand;

  //
  //  protected String status;

  protected List<DigRequestIPAddress> digRequestIPAddresses = new ArrayList<>();

  public DigRequest(NetworkDiagnosticTest networkDiagnosticTest) {

    this.networkDiagnosticTest = networkDiagnosticTest;
  }
}
