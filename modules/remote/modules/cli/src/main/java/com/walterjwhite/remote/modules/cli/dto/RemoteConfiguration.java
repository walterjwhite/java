package com.walterjwhite.remote.modules.cli.dto;


import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.remote.modules.cli.enumeration.RemoteOperatingMode;
import java.time.LocalDateTime;
import javax.jdo.annotations.PersistenceCapable;
import lombok.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class RemoteConfiguration {

  protected Client client;

  protected LocalDateTime dateTime = LocalDateTime.now();

  @EqualsAndHashCode.Exclude protected RemoteOperatingMode remoteOperatingMode;



  public RemoteConfiguration(Client client, RemoteOperatingMode remoteOperatingMode) {

    this.client = client;
    this.remoteOperatingMode = remoteOperatingMode;
  }
}
