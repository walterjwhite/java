package com.walterjwhite.remote.plugins.shell;

import com.walterjwhite.remote.api.model.message.Message;
import com.walterjwhite.shell.api.enumeration.SystemAction;
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
}
