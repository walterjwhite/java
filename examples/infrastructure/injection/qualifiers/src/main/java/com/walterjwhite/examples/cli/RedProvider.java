package com.walterjwhite.examples.cli;

import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

@Red
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class RedProvider implements SomeProvider {
  @Override
  public String get() {
    return "RED";
  }
}
