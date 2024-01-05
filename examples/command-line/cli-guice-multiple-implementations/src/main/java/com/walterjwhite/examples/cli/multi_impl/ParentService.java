package com.walterjwhite.examples.cli.multi_impl;

import jakarta.inject.Inject;

public class ParentService {
  protected final String name;
  protected final ChildService childService;

  @Inject
  public ParentService(String name, ChildService childService) {
    this.name = name;
    this.childService = childService;
  }

  public String print() {
    return "parent." + name + "." + childService.print();
  }
}
