package com.walterjwhite.shell.api.model;


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
public class NetworkInterfaceState {

  protected NetworkInterface networkInterface;

  protected LocalDateTime dateTime = LocalDateTime.now();

  @EqualsAndHashCode.Exclude protected Set<IPAddressState> ipAddressStates = new HashSet<>();
}
