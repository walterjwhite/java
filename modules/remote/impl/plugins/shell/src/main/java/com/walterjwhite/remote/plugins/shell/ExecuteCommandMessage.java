package com.walterjwhite.remote.plugins.shell;

import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.remote.api.model.message.Message;
import java.util.Map;
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
public class ExecuteCommandMessage extends Message {
  // used to set environmental properties
  protected Map<String, String> environmentMap;

  // used to set the user to run a command as
  protected String runAs;

  protected String command;
  // -1 for long-running, time in seconds to let the command run before killing it
  protected int timeout;

  // whether or not to log the return code and output / stderr
  protected boolean captureResponse;

  protected CommandOutputMessage commandOutputMessage;

  public ExecuteCommandMessage(
      Set<Client> recipients,
      int timeToLive,
      String command,
      int timeout,
      boolean captureResponse) {
    super(recipients, timeToLive);
    this.command = command;
    this.timeout = timeout;
    this.captureResponse = captureResponse;
  }

  public ExecuteCommandMessage(
      Client recipient, int timeToLive, String command, int timeout, boolean captureResponse) {
    super(recipient, timeToLive);
    this.command = command;
    this.timeout = timeout;
    this.captureResponse = captureResponse;
  }
}
