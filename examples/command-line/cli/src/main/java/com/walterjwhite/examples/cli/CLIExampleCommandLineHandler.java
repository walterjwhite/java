package com.walterjwhite.examples.cli;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.property.api.annotation.Property;
import com.walterjwhite.property.api.enumeration.SystemProxy;
import com.walterjwhite.property.api.property.ProxyHost;
import com.walterjwhite.property.api.property.ProxyPort;
import jakarta.inject.Inject;
import java.util.Arrays;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CLIExampleCommandLineHandler implements CommandLineHandler {
  protected final String proxyHost;
  protected final Integer proxyPort;

  @Inject
  public CLIExampleCommandLineHandler(

      @Property(ProxyHost.class) String proxyHost,
      @Property(ProxyPort.class) Integer proxyPort) {

    this.proxyHost = proxyHost;
    this.proxyPort = proxyPort;
  }

  @Override
  public void run(String... arguments) {
    LOGGER.info(
        "HttpProxy: {}",
        System.getProperty(
            SystemProxy.HttpProxy.getClass().getName() + "." + SystemProxy.HttpProxy.name()));

    LOGGER.info("proxyHost: {}", proxyHost);
    LOGGER.info("proxyPort: {}", proxyPort);
    executeTest();

    LOGGER.info("arguments: {}", Arrays.stream(arguments));
  }

  protected void executeTest() {

    try {


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
