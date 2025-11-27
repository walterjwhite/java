package com.walterjwhite.remote.plugins.shell;

import com.walterjwhite.remote.api.model.message.Message;
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
}
