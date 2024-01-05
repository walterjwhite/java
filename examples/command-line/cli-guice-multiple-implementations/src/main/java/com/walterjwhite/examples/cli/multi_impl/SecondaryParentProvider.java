package com.walterjwhite.examples.cli.multi_impl;

import jakarta.inject.Inject;
import jakarta.inject.Provider;

public class SecondaryParentProvider implements Provider<ParentService> {
  protected final ChildService childService;
  protected final ParentService parentService;

  @Inject
  public SecondaryParentProvider(ChildService childService) {
    this.childService = childService;
    parentService = new ParentService("secondary", childService);
  }

  @Override
  public ParentService get() {
    return parentService;
  }
}
