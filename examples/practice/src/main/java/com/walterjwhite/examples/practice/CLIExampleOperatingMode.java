package com.walterjwhite.examples.practice;

import com.walterjwhite.inject.cli.property.OperatingMode;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.property.api.annotation.DefaultValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CLIExampleOperatingMode implements OperatingMode {
  @DefaultValue
  Default(PracticeCommandLineHandler.class);

  protected final Class<? extends CommandLineHandler> initiatorClass;
}
