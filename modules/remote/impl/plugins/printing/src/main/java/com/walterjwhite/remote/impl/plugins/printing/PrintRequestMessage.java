package com.walterjwhite.remote.impl.plugins.printing;

import com.walterjwhite.print.model.PrintRequest;
import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.remote.api.model.message.Message;
import java.util.Set;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(doNotUseGetters = true, callSuper = true)
@PersistenceCapable
public class PrintRequestMessage extends Message {
  protected PrintRequest printRequest;

  public PrintRequestMessage(Set<Client> recipients, int timeToLive, PrintRequest printRequest) {
    super(recipients, timeToLive);
    this.printRequest = printRequest;
  }

  public PrintRequestMessage(Client recipient, int timeToLive, PrintRequest printRequest) {
    super(recipient, timeToLive);
    this.printRequest = printRequest;
  }
}
