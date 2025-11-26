package com.walterjwhite.remote.api.model;

import com.walterjwhite.shell.api.model.IPAddress;
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
public class ClientIPAddressHistory {

  protected Client client;

  protected LocalDateTime dateTime = LocalDateTime.now();

  @EqualsAndHashCode.Exclude protected IPAddress ipAddress;

  public ClientIPAddressHistory(Client client, IPAddress ipAddress) {

    this.client = client;
    this.ipAddress = ipAddress;
  }
}
