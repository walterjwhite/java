package com.walterjwhite.infrastructure.inject.providers.spring;

import com.walterjwhite.inject.cli.property.OperatingMode;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.property.api.annotation.DefaultValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SpringBootOperatingMode implements OperatingMode {
  @DefaultValue
  SpringBoot(SpringBootRunner.class);

  private final Class<? extends CommandLineHandler> initiatorClass;
}
