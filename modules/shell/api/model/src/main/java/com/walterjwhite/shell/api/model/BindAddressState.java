package com.walterjwhite.shell.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
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
public class BindAddressState extends AbstractEntity {

  protected LocalDateTime currentDateTime = LocalDateTime.now();

  protected BindAddress bindAddress;

  protected ServiceStatus serviceStatus;

  @EqualsAndHashCode.Exclude protected ShellCommand shellCommand;

  //
  //  public BindAddressState(
  //      BindAddress bindAddress, ServiceStatus serviceStatus, ShellCommand shellCommand) {
  //
  //    this.bindAddress = bindAddress;
  //    this.serviceStatus = serviceStatus;
  //    this.shellCommand = shellCommand;
  //  }
}
