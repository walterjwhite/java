package com.walterjwhite.property.impl;

import com.walterjwhite.property.api.PropertyManager;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractPropertyManager<PropertyClassType extends Object> {
  protected final Reflections reflections;
  protected final PropertyManager propertyManager;

  protected boolean invoked = false;

  public void call() {
    if (invoked) return;

    try {
      for (final Class propertyClass : getClasses()) {
        if (!PropertyHelper.isConcrete(propertyClass)) continue;

        try {
          processClass(propertyClass);
        } catch (InstantiationException
            | IllegalAccessException
            | NoSuchMethodException
            | InvocationTargetException e) {
          LOGGER.warn(String.format("Error configuring: %s", propertyClass), e);
        }
      }
    } catch (Exception e) {
      LOGGER.warn("Error configuring", e);
    }

    invoked = true;
  }

  protected abstract Collection<Class<? extends PropertyClassType>> getClasses();

  protected abstract void processClass(Class<? extends PropertyClassType> targetClass)
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
          InstantiationException;
}
