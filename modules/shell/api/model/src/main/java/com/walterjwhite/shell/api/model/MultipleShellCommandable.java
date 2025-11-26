package com.walterjwhite.shell.api.model;

public interface MultipleShellCommandable {

  void addShellCommand(ShellCommand shellCommand);

  int getTimeout();

  void setTimeout(int timeout);
}
