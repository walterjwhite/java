package com.walterjwhite.examples.cli.jersey;

public class HelloService {
  public String getMessage(final String name) {
    return "Hi " + name;
  }
}
