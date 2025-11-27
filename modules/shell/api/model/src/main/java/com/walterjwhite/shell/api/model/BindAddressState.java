package com.walterjwhite.shell.api.model;

import java.time.LocalDateTime;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
@PersistenceCapable
public class BindAddressState {

  protected LocalDateTime currentDateTime = LocalDateTime.now();

  protected BindAddress bindAddress;

  protected ServiceStatus serviceStatus;

  @EqualsAndHashCode.Exclude protected ShellCommand shellCommand;

}
