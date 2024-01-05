package com.walterjwhite.property.api;

import com.walterjwhite.property.api.property.ConfigurableProperty;
import org.reflections.Reflections;

public interface PropertyManager {
  void initialize();

  //  Properties getProperties();

  Iterable<Class<? extends ConfigurableProperty>> getKeys();

  Reflections getReflections();

  String get(final Class<? extends ConfigurableProperty> configurableProperty);

  void set(Class<? extends ConfigurableProperty> configurableProperty, final String value);

  String lookup(Class<? extends ConfigurableProperty> configurableProperty);

  Class type(Class<? extends ConfigurableProperty> configurableProperty);

  //  String getContextualName(Class<? extends ConfigurableProperty> configurableProperty);
}
