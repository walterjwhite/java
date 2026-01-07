package com.walterjwhite.shell.api.model;

import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
@AllArgsConstructor
@PersistenceCapable
public class CommandOutput {
  @ToString.Exclude protected ShellCommand shellCommand;

  protected int index;

  @EqualsAndHashCode.Exclude protected String output;
}
