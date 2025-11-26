package com.walterjwhite.examples.platform.agent;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class MainCommandLineHandler implements CommandLineHandler {
  @Override
  public void run(final String... arguments) {
    System.out.println(
        "HERE - THIS SHOULD NOT BE PRINTED UNLESS the application is run without the agent");
    System.exit(2);
  }
}
