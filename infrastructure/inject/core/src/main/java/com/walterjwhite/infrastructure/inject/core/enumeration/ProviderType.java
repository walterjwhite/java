package com.walterjwhite.infrastructure.inject.core.enumeration;

import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
import java.lang.reflect.InvocationTargetException;

public enum ProviderType {
  Self {
    protected Class[] getConstructorArguments(final Object... arguments) {
      final Class[] argumentTypes = new Class[arguments.length];
      for (int i = 0; i < arguments.length; i++) {
        argumentTypes[i] = arguments[i].getClass();
      }

      return argumentTypes;
    }

    public <Type> Type get(final Class<Type> targetClass, final Object... arguments) {
      final Class[] constructorArguments = getConstructorArguments(arguments);

      try {
        return targetClass.getConstructor(constructorArguments).newInstance(arguments);

      } catch (IllegalArgumentException
          | InstantiationException
          | IllegalAccessException
          | InvocationTargetException
          | NoSuchMethodException e) {
        throw new Error("Unable to create instance of:" + targetClass, e); //
      }
    }
  },
  Injector {
    @Override
    public <Type> Type get(Class<Type> targetClass, Object... arguments) {
      return ApplicationHelper.getApplicationInstance().getInjector().getInstance(targetClass);
    }
  };

  public abstract <Type> Type get(final Class<Type> targetClass, final Object... arguments);
}
