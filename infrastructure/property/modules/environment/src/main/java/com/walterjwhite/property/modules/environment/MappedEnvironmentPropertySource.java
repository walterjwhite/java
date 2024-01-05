package com.walterjwhite.property.modules.environment;

import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.property.api.PropertyPair;
import com.walterjwhite.property.api.annotation.PropertySourceIndex;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import com.walterjwhite.property.api.property.MappedEnvironmentProperty;
import com.walterjwhite.property.impl.source.AbstractPropertySource;
import java.util.HashSet;
import java.util.Set;

@PropertySourceIndex(2)
public class MappedEnvironmentPropertySource
    extends AbstractPropertySource<MappedEnvironmentProperty, Set<PropertyPair>> {
  public MappedEnvironmentPropertySource(PropertyManager propertyManager) {
    super(propertyManager, MappedEnvironmentProperty.class);
  }

  // ie. sets the ProxyHost or ProxyPort from http_proxy or https_proxy ...
  @Override
  protected Set<PropertyPair> get(Class<? extends MappedEnvironmentProperty> property) {
    final Set<PropertyPair> propertyPairs = new HashSet<>();

    if (property.getEnumConstants() == null) return propertyPairs;

    for (final MappedEnvironmentProperty mappedEnvironmentProperty1 : property.getEnumConstants()) {
      for (final Class<? extends ConfigurableProperty> supportedProperty :
          mappedEnvironmentProperty1.getSupportedProperties()) {
        propertyPairs.add(
            new PropertyPair(
                supportedProperty, mappedEnvironmentProperty1.getValue(supportedProperty)));
      }
    }

    return propertyPairs;
  }

  @Override
  protected void doSet(
      Class<? extends MappedEnvironmentProperty> propertyType, Set<PropertyPair> propertyPairs) {
    for (PropertyPair propertyPair : propertyPairs) {
      propertyManager.set(propertyPair.getConfigurablePropertyClass(), propertyPair.getValue());
    }
  }
}
