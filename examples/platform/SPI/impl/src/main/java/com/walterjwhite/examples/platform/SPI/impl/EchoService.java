package com.walterjwhite.examples.platform.SPI.impl;

import com.walterjwhite.examples.platform.SPI.api.SampleService;

public class EchoService implements SampleService {
  @Override
  public String print(String input) {
    return "Hi " + input;
  }
}
