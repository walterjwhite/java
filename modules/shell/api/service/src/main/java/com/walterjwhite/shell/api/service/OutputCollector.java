package com.walterjwhite.shell.api.service;

public interface OutputCollector {
  void onData(final String line, final boolean isError);
}
