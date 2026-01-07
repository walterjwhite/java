package com.walterjwhite.inject.cli;

import com.walterjwhite.infrastructure.inject.core.ApplicationArgumentAware;
import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
import com.walterjwhite.inject.cli.service.CLIParser;
import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.property.api.annotation.PropertySourceIndex;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import com.walterjwhite.property.impl.source.AbstractSingularStringPropertySource;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@PropertySourceIndex(10)
public class CLIPropertySource extends AbstractSingularStringPropertySource<ConfigurableProperty> {
  public CLIPropertySource(PropertyManager propertyManager) {
    super(propertyManager, ConfigurableProperty.class);

    setApplicationArguments();
  }

  private void setApplicationArguments() {
    if (ApplicationHelper.getApplicationInstance()
        instanceof ApplicationArgumentAware applicationArgumentAware) {
      final CLIParser cliParser = getCLIParser();
      setCommandLineProperties(
          cliParser.parseCommandLineArguments(
              propertyManager.getKeys(), applicationArgumentAware.getArguments()));
    }
  }

  protected CLIParser getCLIParser() {
    try {
      return propertyManager
          .getReflections()
          .getSubTypesOf(CLIParser.class)
          .iterator()
          .next()
          .getDeclaredConstructor()
          .newInstance();
    } catch (IllegalAccessException
        | InstantiationException
        | NoSuchMethodException
        | InvocationTargetException e) {
      throw new CLIConfigurationException(
          "Application is mis-configured - CLIParser should have a public, no-arg constructor");
    }
  }

  protected void setCommandLineProperties(
      final Map<Class<? extends ConfigurableProperty>, String> configurablePropertyMap) {
    for (final Class<? extends ConfigurableProperty> configurableProperty :
        configurablePropertyMap.keySet())
      propertyManager.set(configurableProperty, configurablePropertyMap.get(configurableProperty));
  }

  @Override
  protected String doGet(final String propertyKey) {
    return null;
  }
}
