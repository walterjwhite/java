package com.walterjwhite.inject.cli.util;

import com.walterjwhite.infrastructure.inject.core.Injector;
import com.walterjwhite.inject.cli.CLIConfigurationException;
import com.walterjwhite.property.api.SecretService;
import com.walterjwhite.property.impl.PropertyHelper;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

@Slf4j
public class CommandLineUtil {
  public static Injector getInjector(Reflections reflections)
      throws IllegalAccessException, InstantiationException, NoSuchMethodException,
          InvocationTargetException {
    return reflections
        .getSubTypesOf(Injector.class)
        .iterator()
        .next()
        .getDeclaredConstructor()
        .newInstance();
  }

  //    public static Class<? extends AbstractCommandLineHandler> getCommandLineHandlerClass(
  //            final Reflections reflections, final PropertyManager propertyManager) {
  //        return getCommandLineHandlerClass(reflections, propertyManager,
  // AbstractCommandLineHandler.class);
  //    }

  //  public static void setHandlerArguments(final String[] handlerArguments) {
  //    HANDLER_ARGUMENTS = handlerArguments;
  //  }
  //
  //  public static String[] getHandlerArguments() {
  //    return HANDLER_ARGUMENTS;
  //  }

  // this was designed to have a different implementation for test / local
  public static SecretService getSecretService(final Reflections reflections)
      throws IllegalAccessException, InstantiationException, NoSuchMethodException,
          InvocationTargetException {
    // use the first available option
    final Iterator<Class<? extends SecretService>> secretServiceIterator =
        reflections.getSubTypesOf(SecretService.class).iterator();

    if (secretServiceIterator.hasNext()) {
      final Class<? extends SecretService> secretServiceClass = secretServiceIterator.next();
      if (PropertyHelper.isConcrete(secretServiceClass)) {
        return secretServiceClass.getDeclaredConstructor().newInstance();
      }
    }

    throw new CLIConfigurationException("Application does not have a secret service configured.");
  }
}
