package com.walterjwhite.infrastructure.inject.providers.spring;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.ConfigurableWebServerApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.ConfigurableWebApplicationContext;

@SpringBootApplication(scanBasePackages = {"com.walterjwhite"})
// the below does not work
// @Component
public class SpringBootRunner implements CommandLineHandler {
  //  @Inject
  //  public SpringBootRunner(/*
  //      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
  //    super(shutdownTimeoutInSeconds);
  //    */ ) {
  //    super(15);
  //  }

  @Override
  public void run(String... arguments) throws InterruptedException {
    final ConfigurableApplicationContext configurableApplicationContext =
        SpringApplication.run(SpringBootRunner.class, arguments);
    if (isWebServer(configurableApplicationContext)) {
      Thread.currentThread().join();
    }
  }

  protected boolean isWebServer(
      final ConfigurableApplicationContext configurableApplicationContext) {
    /*AnnotationConfigServletWebServerApplicationContext*/
    if (ConfigurableWebServerApplicationContext.class.isInstance(configurableApplicationContext)) {
      return true;
    }
    if (ConfigurableWebApplicationContext.class.isInstance(configurableApplicationContext)) {
      return true;
    }

    return false;
  }
}
