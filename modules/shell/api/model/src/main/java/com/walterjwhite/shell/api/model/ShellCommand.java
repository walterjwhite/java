package com.walterjwhite.shell.api.model;

import java.time.LocalDateTime;
import java.util.*;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
@PersistenceCapable
public class ShellCommand implements EnvironmentAware {

  protected Node node;

  protected String commandLine;

  protected String workingDirectory;

  protected LocalDateTime dateTime = LocalDateTime.now();

  @EqualsAndHashCode.Exclude protected int timeout;

  @EqualsAndHashCode.Exclude
  protected Set<ShellCommandEnvironmentProperty> shellCommandEnvironmentProperties =
      new HashSet<>();

  @EqualsAndHashCode.Exclude protected int returnCode;

  @EqualsAndHashCode.Exclude protected List<CommandOutput> outputs = new ArrayList<>();

  @EqualsAndHashCode.Exclude protected List<CommandError> errors = new ArrayList<>();

  public ShellCommand withTimeout(int timeout) {
    this.timeout = timeout;
    return this;
  }

  public ShellCommand withCommandLine(final String commandLine) {
    this.commandLine = commandLine;
    return this;
  }

  public ShellCommand withWorkingDirectory(final String workingDirectory) {
    this.workingDirectory = workingDirectory;
    return this;
  }
}
