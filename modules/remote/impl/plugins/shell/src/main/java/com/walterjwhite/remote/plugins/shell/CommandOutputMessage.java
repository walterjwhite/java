package com.walterjwhite.remote.plugins.shell;

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
@ToString(doNotUseGetters = true, callSuper = true)
@NoArgsConstructor
@PersistenceCapable
public class CommandOutputMessage extends Message {
  protected ExecuteCommandMessage executeCommandMessage;
  protected String stdout;
  protected String stderr;
  protected int returnCode;

  public CommandOutputMessage(
      Set<Client> recipients,
      int timeToLive,
      ExecuteCommandMessage executeCommandMessage,
      String stdout,
      String stderr,
      int returnCode) {
    super(recipients, timeToLive);
    this.executeCommandMessage = executeCommandMessage;
    this.stdout = stdout;
    this.stderr = stderr;
    this.returnCode = returnCode;
  }

  public CommandOutputMessage(
      Client recipient,
      int timeToLive,
      ExecuteCommandMessage executeCommandMessage,
      String stdout,
      String stderr,
      int returnCode) {
    super(recipient, timeToLive);
    this.executeCommandMessage = executeCommandMessage;
    this.stdout = stdout;
    this.stderr = stderr;
    this.returnCode = returnCode;
  }
}
