package com.walterjwhite.identity.cli;

import com.walterjwhite.inject.cli.CLIApplication;

public enum TokenType {
  First {
    public String get() {
      return CLIApplication.getRawArguments()[0];
    }
  };

  public abstract String get();
}
