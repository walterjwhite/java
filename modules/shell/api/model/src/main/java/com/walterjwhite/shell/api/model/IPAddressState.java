package com.walterjwhite.shell.api.model;


import java.time.LocalDateTime;
import javax.jdo.annotations.PersistenceCapable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@AllArgsConstructor
@NoArgsConstructor
@PersistenceCapable
public class IPAddressState {

  protected LocalDateTime currentDateTime = LocalDateTime.now();

  protected IPAddress ipAddress;

  protected NetworkInterfaceState networkInterfaceState;
}
