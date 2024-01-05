package com.walterjwhite.examples.cli.multi_impl;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;

public class MultiImplCommandLineHandler implements CommandLineHandler {
  protected final ParentService primaryParentService;
  protected final ParentService secondaryParentService;

  @Inject
  public MultiImplCommandLineHandler(
      //      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      @Primary ParentService primaryParentService,
      @Secondary ParentService secondaryParentService) {
    //    super(shutdownTimeoutInSeconds);
    this.primaryParentService = primaryParentService;
    this.secondaryParentService = secondaryParentService;
  }

  @Override
  public void run(String... arguments) {
    primaryParentService.print();
    secondaryParentService.print();
  }
}
