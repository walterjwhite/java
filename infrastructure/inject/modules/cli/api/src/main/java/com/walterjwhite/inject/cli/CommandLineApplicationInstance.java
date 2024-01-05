package com.walterjwhite.inject.cli;

import com.walterjwhite.infrastructure.inject.core.ApplicationInstance;
import com.walterjwhite.infrastructure.inject.core.Injector;
import com.walterjwhite.infrastructure.inject.core.service.ServiceManager;
import com.walterjwhite.inject.cli.property.OperatingMode;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.property.api.SecretService;
import com.walterjwhite.property.impl.PropertyHelper;
import java.util.Iterator;
import java.util.Optional;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

// command-line, the remaining, unprocessed arguments are to be passed to the handler)
// removed because it isn't used currently and may impact dependencies
@Slf4j
@Getter
public class CommandLineApplicationInstance extends ApplicationInstance {
  //  protected final Class<? extends OperatingMode> operatingModeClass;
  protected transient Class<? extends CommandLineHandler> commandLineHandlerClass;

  protected final String[] arguments;

  public CommandLineApplicationInstance(
      Reflections reflections,
      PropertyManager propertyManager,
      ServiceManager serviceManager,
      SecretService secretService,
      Injector injector,
      String[] arguments) {
    super(reflections, propertyManager, serviceManager, secretService, injector);
    this.arguments = arguments;
  }

  // protected final String[] handlerArguments;

  /** This must be set after property registration has taken place */
  @Override
  protected void initialize() throws Exception {
    super.initialize();

    final Optional<Class<? extends CommandLineHandler>> operatingModeInitiatorClass =
        getOperatingModeInitiator();
    if (operatingModeInitiatorClass.isPresent()) {
      commandLineHandlerClass = operatingModeInitiatorClass.get();
      return;
    }

    final Optional<Class<? extends CommandLineHandler>> commandLineHandlerClassInstance =
        getCommandLineHandlerClass();
    if (commandLineHandlerClassInstance.isPresent()) {
      commandLineHandlerClass = commandLineHandlerClassInstance.get();
      return;
    }

    throw new CLIConfigurationException(
        "Application is improperly configured, there must be one and only one OperatingMode class"
            + " in the classpath");
  }

  protected Optional<Class<? extends CommandLineHandler>> getOperatingModeInitiator() {
    final Iterator<Class<? extends OperatingMode>> operatingModeIterator =
        reflections.getSubTypesOf(OperatingMode.class).iterator();

    if (operatingModeIterator.hasNext()) {
      final Class<? extends OperatingMode> operatingModeClass = operatingModeIterator.next();
      final OperatingMode operatingMode = getOperatingMode(operatingModeClass);
      return Optional.of(operatingMode.getInitiatorClass());
    }

    return Optional.empty();
  }

  protected OperatingMode getOperatingMode(
      final Class<? extends OperatingMode> operatingModeClass) {
    return (OperatingMode)
        Enum.valueOf(
            (Class<? extends Enum>) operatingModeClass, propertyManager.get(operatingModeClass));
  }

  // Selects first non-null command-line handler class
  protected Optional<Class<? extends CommandLineHandler>> getCommandLineHandlerClass() {
    // use the first available option
    return reflections.getSubTypesOf(CommandLineHandler.class).stream()
        .filter(c -> PropertyHelper.isConcrete(c))
        .findFirst();
  }

  protected void doRun() throws Exception {
    final CommandLineHandler commandLineHandler =
        getInjector().getInstance(commandLineHandlerClass);

    commandLineHandler.run(arguments);
    System.exit(0);
  }
}
