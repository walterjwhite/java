package com.walterjwhite.examples.cli;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.property.api.enumeration.SystemProxy;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class CLIExampleCommandLineHandler implements CommandLineHandler {
  @Override
  public void run(String... arguments) {
    LOGGER.info(
        "HttpProxy: {}",
        System.getProperty(
            SystemProxy.HttpProxy.getClass().getName() + "." + SystemProxy.HttpProxy.name()));

    LOGGER.info("arguments: {}", arguments);
  }
}
