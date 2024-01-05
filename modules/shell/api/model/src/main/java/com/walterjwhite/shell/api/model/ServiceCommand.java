package com.walterjwhite.shell.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.shell.api.enumeration.ServiceAction;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class ServiceCommand extends AbstractEntity implements ShellCommandable {

  protected Service service;

  protected ServiceAction serviceAction;

  @EqualsAndHashCode.Exclude protected int timeout;

  protected ShellCommand shellCommand;

  @EqualsAndHashCode.Exclude protected ServiceStatus serviceStatus;

  public ServiceCommand withService(Service service) {
    this.service = service;
    return this;
  }

  public ServiceCommand withServiceAction(ServiceAction serviceAction) {
    this.serviceAction = serviceAction;
    return this;
  }
}
