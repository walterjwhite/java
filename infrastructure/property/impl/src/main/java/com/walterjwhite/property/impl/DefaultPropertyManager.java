package com.walterjwhite.property.impl;

import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.property.api.PropertyNameLookupService;
import com.walterjwhite.property.api.SecretService;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import com.walterjwhite.property.impl.source.PropertySourceManager;
import com.walterjwhite.property.impl.tgt.PropertyTargetManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.reflections.Reflections;

@Getter
@RequiredArgsConstructor
public class DefaultPropertyManager implements PropertyManager {
  protected final PropertySourceManager propertySourceManager;
  protected final PropertyTargetManager propertyTargetManager;

  protected final PropertyNameLookupService propertyNameLookupService;
  protected final Reflections reflections;

  public DefaultPropertyManager(
      PropertyNameLookupService propertyNameLookupService,
      Reflections reflections,
      SecretService secretService) {
    this.propertyNameLookupService = propertyNameLookupService;
    this.reflections = reflections;

    this.propertySourceManager = new PropertySourceManager(reflections, this, secretService);
    this.propertyTargetManager = new PropertyTargetManager(reflections, this);
  }

  public void initialize() {
    propertySourceManager.call();
    propertyTargetManager.call();
  }

  public Iterable<Class<? extends ConfigurableProperty>> getKeys() {
    return propertySourceManager.getKeys();
  }

  public String get(final Class<? extends ConfigurableProperty> configurableProperty) {
    return propertySourceManager.get(configurableProperty);
  }

  @Override
  public void set(Class<? extends ConfigurableProperty> configurableProperty, String value) {
    propertySourceManager.set(configurableProperty, value);
  }

  public String lookup(final Class<? extends ConfigurableProperty> configurableProperty) {
    return propertyNameLookupService.lookup(configurableProperty);
  }

  @Override
  public Class type(Class<? extends ConfigurableProperty> configurableProperty) {
    return propertySourceManager.type(configurableProperty);
  }
}
