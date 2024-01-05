package com.walterjwhite.examples.cli.multi_impl;

import com.google.inject.Binder;
import com.walterjwhite.infrastructure.inject.providers.guice.GuiceApplicationModule;

public class CLIExampleApplicationModule implements GuiceApplicationModule {
  @Override
  public void configure(Binder binder) {
    binder
        .bind(ParentService.class)
        .annotatedWith(Primary.class)
        .toProvider(PrimaryParentProvider.class);
    binder
        .bind(ParentService.class)
        .annotatedWith(Secondary.class)
        .toProvider(SecondaryParentProvider.class);

    binder.bind(ChildService.class);
  }
}
