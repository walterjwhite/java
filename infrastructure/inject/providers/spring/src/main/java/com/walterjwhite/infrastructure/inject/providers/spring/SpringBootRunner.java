package com.walterjwhite.infrastructure.inject.providers.spring;

import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = {"com.walterjwhite"})
public class SpringBootRunner implements CommandLineHandler {
  @Override
  public void run(String... arguments) throws InterruptedException {
    final ConfigurableApplicationContext configurableApplicationContext =
        SpringApplication.run(SpringBootRunner.class, arguments);
    SpringApplicationModule springApplicationModule =
        ApplicationHelper.getApplicationInstance()
            .getInjector()
            .getInstance(SpringApplicationModule.class);
    if (springApplicationModule.isWebServer()) {
      Thread.currentThread().join();
    }
  }
}
