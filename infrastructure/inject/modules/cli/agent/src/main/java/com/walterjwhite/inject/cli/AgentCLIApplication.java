package com.walterjwhite.inject.cli;

import com.walterjwhite.infrastructure.inject.core.Injector;
import com.walterjwhite.infrastructure.inject.core.service.ServiceManager;
import com.walterjwhite.inject.cli.util.CommandLineUtil;
import com.walterjwhite.property.api.SecretService;
import com.walterjwhite.property.impl.DefaultPropertyManager;
import com.walterjwhite.property.impl.DefaultPropertyNameLookupService;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.InvocationTargetException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.reflections.Reflections;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AgentCLIApplication {
  public static void premain(final String arguments, final Instrumentation instrumentation) {
    instrument(arguments, instrumentation);
  }

  public static void agentmain(final String arguments, final Instrumentation instrumentation) {
    instrument(arguments, instrumentation);
  }

  @SneakyThrows
  private static void instrument(final String arguments, final Instrumentation instrumentation) {
    final Reflections reflections = Reflections.collect();

    try {
      final SecretService secretService = CommandLineUtil.getSecretService(reflections);
      final Injector injector = CommandLineUtil.getInjector(reflections);

      new AgentApplicationInstance(
              reflections,
              new DefaultPropertyManager(
                  new DefaultPropertyNameLookupService(), reflections, secretService),
              new ServiceManager(reflections, injector),
              secretService,
              injector,
              instrumentation,
              arguments)
          .run();
    } catch (IllegalAccessException
        | InstantiationException
        | NoSuchMethodException
        | InvocationTargetException e) {
      throw new Error("Error running agent", e);
    }
  }
}
