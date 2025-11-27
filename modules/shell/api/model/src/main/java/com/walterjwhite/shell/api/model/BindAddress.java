package com.walterjwhite.shell.api.model;

import com.walterjwhite.shell.api.enumeration.Protocol;
import javax.jdo.annotations.PersistenceCapable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@AllArgsConstructor
@NoArgsConstructor
@PersistenceCapable
public class BindAddress {

  protected Protocol protocol;

  protected int port;

  protected IPAddress ipAddress;
}
