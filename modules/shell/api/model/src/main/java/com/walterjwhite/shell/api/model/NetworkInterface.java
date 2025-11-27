package com.walterjwhite.shell.api.model;

import javax.jdo.annotations.PersistenceCapable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class NetworkInterface {

  protected Node node;

  protected String interfaceName;

}
