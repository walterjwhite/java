package com.walterjwhite.remote.plugins.shell;

import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.remote.api.model.message.Message;
import com.walterjwhite.shell.api.enumeration.ServiceAction;
import com.walterjwhite.shell.api.model.Service;
import java.util.Set;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@NoArgsConstructor
@PersistenceCapable
public class ServiceMessage extends Message {

  protected Service service;

  protected ServiceAction serviceAction;

  public ServiceMessage(
      Set<Client> recipients, int timeToLive, Service service, ServiceAction serviceAction) {
    super(recipients, timeToLive);

    this.service = service;
    this.serviceAction = serviceAction;
  }

  public ServiceMessage(
      Client recipient, int timeToLive, Service service, ServiceAction serviceAction) {
    super(recipient, timeToLive);

    this.service = service;
    this.serviceAction = serviceAction;
  }
}
