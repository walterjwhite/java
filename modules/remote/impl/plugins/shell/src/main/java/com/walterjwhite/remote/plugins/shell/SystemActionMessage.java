package com.walterjwhite.remote.plugins.shell;

import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.remote.api.model.message.Message;
import com.walterjwhite.shell.api.enumeration.SystemAction;
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
public class SystemActionMessage extends Message {
  protected SystemAction systemAction;

  public SystemActionMessage(Set<Client> recipients, int timeToLive, SystemAction systemAction) {
    super(recipients, timeToLive);

    this.systemAction = systemAction;
  }

  public SystemActionMessage(Client recipient, int timeToLive, SystemAction systemAction) {
    super(recipient, timeToLive);

    this.systemAction = systemAction;
  }
}
