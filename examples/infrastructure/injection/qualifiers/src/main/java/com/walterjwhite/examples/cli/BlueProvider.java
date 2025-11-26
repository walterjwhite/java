package com.walterjwhite.examples.cli;

import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

@Blue
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class BlueProvider implements SomeProvider {
  @Override
  public String get() {
    return "BLUE";
  }
}
