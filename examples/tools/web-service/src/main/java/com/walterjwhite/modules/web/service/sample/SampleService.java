package com.walterjwhite.modules.web.service.sample;

public class SampleService {
  public String sayHello(final String name) {
    if (name != null) return "hi " + name;

    return "";
  }
}
