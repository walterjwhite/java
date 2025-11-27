package com.walterjwhite.remote.plugins.shell;

import com.walterjwhite.remote.api.model.message.Message;
import com.walterjwhite.shell.api.enumeration.ServiceAction;
import com.walterjwhite.shell.api.model.Service;
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
}
