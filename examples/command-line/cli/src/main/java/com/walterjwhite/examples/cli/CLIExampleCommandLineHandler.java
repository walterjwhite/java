package com.walterjwhite.examples.cli;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.property.api.annotation.Property;
import com.walterjwhite.property.api.enumeration.SystemProxy;
import com.walterjwhite.property.api.property.ProxyHost;
import com.walterjwhite.property.api.property.ProxyPort;
import java.util.Arrays;
import java.util.Optional;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CLIExampleCommandLineHandler implements CommandLineHandler {
  protected final Optional<String> proxyHost;
  protected final Optional<Integer> proxyPort;

  @Inject
  public CLIExampleCommandLineHandler(
      //      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      @Property(ProxyHost.class) Optional proxyHost,
      @Property(ProxyPort.class) Optional proxyPort) {
    //    super(shutdownTimeoutInSeconds);
    this.proxyHost = proxyHost;
    this.proxyPort = proxyPort;
  }

  @Override
  public void run(String... arguments) {
    LOGGER.info(
        "HttpProxy: {}",
        System.getProperty(
            SystemProxy.HttpProxy.getClass().getName() + "." + SystemProxy.HttpProxy.name()));

    if (proxyHost.isPresent()) {
      LOGGER.info("proxyHost: {}", proxyHost.get());
    } else {
      LOGGER.info("No proxyHost is specified");
    }
    if (proxyPort.isPresent()) {
      LOGGER.info("proxyPort: {}", proxyPort.get());
    } else {
      LOGGER.info("No proxyPort is specified");
    }

    executeTest();

    LOGGER.info("arguments: {}", Arrays.stream(arguments));
  }

  protected void executeTest() {
    // no, use process builder here
    try {
      //      System.getenv().put("GOPATH", "/usr/local");

      final Process process =
          Runtime.getRuntime()
              .exec("go get -u" + " github.com.walterjwhite/go-application/commands/secrets/find...");
      process.waitFor();
    } catch (Exception e) {
      LOGGER.warn("Error", e);
      Thread.currentThread().interrupt();
    }
  }
}
