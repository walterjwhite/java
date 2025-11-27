package com.walterjwhite.shell.api.model;

import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

@Data
@ToString(doNotUseGetters = true)
@AllArgsConstructor
@NoArgsConstructor
@PersistenceCapable
public class ShellCommandEnvironmentProperty {

  protected ShellCommand shellCommand;

  protected String key;

  @EqualsAndHashCode.Exclude protected String value;
}
