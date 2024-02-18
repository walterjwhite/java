package com.walterjwhite.infrastructure.inject.providers.spring;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.ConfigurableWebServerApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.ConfigurableWebApplicationContext;


@SpringBootApplication(scanBasePackages = {"com.walterjwhite"})


public class SpringBootRunner implements CommandLineHandler {





  



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
    
    if (ConfigurableWebServerApplicationContext.class.isInstance(configurableApplicationContext)) {
      return true;
    }
    if (ConfigurableWebApplicationContext.class.isInstance(configurableApplicationContext)) {
      return true;
    }

    return false;
  }
}
