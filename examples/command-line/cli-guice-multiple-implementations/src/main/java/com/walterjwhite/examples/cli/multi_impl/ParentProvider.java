package com.walterjwhite.examples.cli.multi_impl;

import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

// compatible with Guice and CDI
// I think CDI will also require some special "glue"
@Deprecated
public class ParentProvider {
  @Inject
  @Produces
  public ParentService producePrimary(ChildService childService) {
    return new ParentService("primary", childService);
  }

  @Inject
  @Secondary
  @Produces
  public ParentService produceSecondary(ChildService childService) {
    return new ParentService("secondary", childService);
  }
}
