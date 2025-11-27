package com.walterjwhite.property.impl.source;

import com.walterjwhite.logging.annotation.Sensitive;
import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.property.api.SecretService;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import com.walterjwhite.property.api.source.PropertySource;
import com.walterjwhite.property.impl.AbstractPropertyManager;
import com.walterjwhite.property.impl.PropertyHelper;
import com.walterjwhite.property.impl.PropertySourceComparator;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import lombok.Getter;
import org.reflections.Reflections;

@Getter
public class PropertySourceManager extends AbstractPropertyManager<PropertySource> {
  protected final EncryptedPropertySourceManager encryptedPropertySourceManager;
  protected final Map<Class<? extends ConfigurableProperty>, PropertyValue> propertyValueMap =
      new HashMap<>();

  public PropertySourceManager(
      Reflections reflections, final PropertyManager propertyManager, SecretService secretService) {
    super(reflections, propertyManager);
    this.encryptedPropertySourceManager = new EncryptedPropertySourceManager(secretService);
  }

  public void call() {
    super.call();

    encryptedPropertySourceManager.decryptProperties(propertyValueMap);
    validateProperties();
  }

  public Iterable<Class<? extends ConfigurableProperty>> getKeys() {
    return (reflections.getSubTypesOf(ConfigurableProperty.class));
  }

  protected void validateProperties() {
    getKeys().forEach(p -> PropertyHelper.validatePropertyConfiguration(p, get(p)));
  }

  protected List<Class<? extends PropertySource>> getClasses() {
    final List<Class<? extends PropertySource>> orderedSourceClasses = new ArrayList<>();
    orderedSourceClasses.addAll(reflections.getSubTypesOf(PropertySource.class));
    Collections.sort(orderedSourceClasses, new PropertySourceComparator());

    return orderedSourceClasses;
  }

  protected void processClass(Class<? extends PropertySource> targetClass)
      throws NoSuchMethodException,
          IllegalAccessException,
          InvocationTargetException,
          InstantiationException {
    final PropertySource propertySource =
        targetClass.getConstructor(PropertyManager.class).newInstance(propertyManager);
    propertySource.get();
  }

  public void set(
      Class<? extends ConfigurableProperty> configurableProperty, @Sensitive final String value) {
    if (PropertyHelper.isSensitive(configurableProperty)) {
      encryptedPropertySourceManager.setSensitiveProperty(configurableProperty, value);
    } else {
      setProperty(configurableProperty, value);
    }
  }

  protected void setProperty(
      final Class<? extends ConfigurableProperty> configurableProperty,
      @Sensitive final String value) {
    propertyValueMap.put(
        configurableProperty,
        new PropertyValue(PropertyHelper.getPropertyValueType(configurableProperty), value));
  }

  @Sensitive
  public String get(final Class<? extends ConfigurableProperty> configurableProperty) {
    final PropertyValue defaultPropertyValue = propertyValueMap.get(configurableProperty);
    if (defaultPropertyValue != null) {
      return defaultPropertyValue.getValue();
    }

    return null;
  }

  public Class type(final Class<? extends ConfigurableProperty> configurableProperty) {
    final PropertyValue defaultPropertyValue = propertyValueMap.get(configurableProperty);
    if (defaultPropertyValue != null) {
      return defaultPropertyValue.getPropertyType();
    }

    return null;
  }
}
