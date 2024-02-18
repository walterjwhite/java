package com.walterjwhite.remote.plugins.shell;

import com.walterjwhite.remote.api.model.message.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.jdo.annotations.PersistenceCapable;
import java.util.Map;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@NoArgsConstructor
@PersistenceCapable
public class ExecuteCommandMessage extends Message {

  protected Map<String, String> environmentMap;


  protected String runAs;

  protected String command;

  protected int timeout;


  protected boolean captureResponse;

  protected CommandOutputMessage commandOutputMessage;
}
