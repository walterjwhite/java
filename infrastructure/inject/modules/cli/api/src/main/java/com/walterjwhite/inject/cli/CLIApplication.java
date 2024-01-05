package com.walterjwhite.inject.cli;

import com.walterjwhite.infrastructure.inject.core.Injector;
import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
import com.walterjwhite.infrastructure.inject.core.service.ServiceManager;
import com.walterjwhite.inject.cli.util.CommandLineUtil;
import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.property.api.SecretService;
import com.walterjwhite.property.impl.DefaultPropertyManager;
import com.walterjwhite.property.impl.DefaultPropertyNameLookupService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

/** This is configured to launch the application whether it is CDI, Guice, etc. - via pom.xml */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CLIApplication {
  public static void main(final String[] arguments) throws Exception {
    try {
      final Reflections reflections = Reflections.collect();

      final SecretService secretService = CommandLineUtil.getSecretService(reflections);
      final Injector injector = CommandLineUtil.getInjector(reflections);
      final PropertyManager propertyManager =
          new DefaultPropertyManager(
              new DefaultPropertyNameLookupService(), reflections, secretService);

      final ServiceManager serviceManager = new ServiceManager(reflections, injector);

      new CommandLineApplicationInstance(
              reflections,
              propertyManager,
              serviceManager,
              secretService,
              injector,
              // this may potentially pull in the agents ...
              arguments)
          .run();
    } catch (Exception e) {
      LOGGER.error("Error running", e);
    }
  }

  public static String[] getRawArguments() {
    return ((CommandLineApplicationInstance) ApplicationHelper.getApplicationInstance())
        .getArguments();
  }
}
