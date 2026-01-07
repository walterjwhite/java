package com.walterjwhite.shell.api.model;

import com.walterjwhite.shell.api.enumeration.ServiceState;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
@AllArgsConstructor
@PersistenceCapable
public class ServiceStatus {

  protected Service service;

  protected LocalDateTime dateTime = LocalDateTime.now();

  @EqualsAndHashCode.Exclude protected ServiceState state;

  @EqualsAndHashCode.Exclude protected Set<BindAddressState> bindAddressStates = new HashSet<>();

  @EqualsAndHashCode.Exclude protected ShellCommand shellCommand;

  public ServiceStatus(
      Service service,
      ServiceState state,
      Set<BindAddressState> addresses,
      ShellCommand shellCommand) {}
}
