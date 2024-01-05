package com.walterjwhite.property.impl.source;

import com.walterjwhite.logging.annotation.Sensitive;
import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.property.api.SecretService;
import com.walterjwhite.property.api.annotation.PropertyValueType;
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
  protected final Map<Class<? extends ConfigurableProperty>, PropertyValue> propertyValueMap =
      new HashMap<>();
  protected final Map<Class<? extends ConfigurableProperty>, PropertyValue>
      encryptedPropertyValueMap = new HashMap<>();

  protected final SecretService secretService;

  public PropertySourceManager(
      Reflections reflections, final PropertyManager propertyManager, SecretService secretService) {
    super(reflections, propertyManager);
    this.secretService = secretService;
  }

  public void call() {
    super.call();

    decryptProperties();
    validateProperties();
  }

  public Iterable<Class<? extends ConfigurableProperty>> getKeys() {
    return (reflections.getSubTypesOf(ConfigurableProperty.class));
  }

  protected void validateProperties() {
    getKeys().forEach(p -> validateProperty(p));
  }

  protected void validateProperty(
      final Class<? extends ConfigurableProperty> configurablePropertyClass) {
    final String value = get(configurablePropertyClass);

    PropertyHelper.validatePropertyConfiguration(configurablePropertyClass, value);
  }

  protected List<Class<? extends PropertySource>> getClasses() {
    final List<Class<? extends PropertySource>> orderedSourceClasses = new ArrayList<>();
    orderedSourceClasses.addAll(reflections.getSubTypesOf(PropertySource.class));
    Collections.sort(orderedSourceClasses, new PropertySourceComparator());

    return orderedSourceClasses;
  }

  protected void processClass(Class<? extends PropertySource> targetClass)
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
          InstantiationException {
    final PropertySource propertySource =
        targetClass.getConstructor(PropertyManager.class).newInstance(propertyManager);
    propertySource.get();
  }

  protected void decryptProperties() {
    try {
      for (final Map.Entry<Class<? extends ConfigurableProperty>, PropertyValue> entry :
          encryptedPropertyValueMap.entrySet()) {
        final Class<? extends ConfigurableProperty> keyName = entry.getKey();
        final String encryptedValue = entry.getValue().getValue();

        if (encryptedValue == null)
          throw new IllegalArgumentException("Encrypted value is null, unable to decrypt it.");

        final String plaintextValue = decryptProperty(encryptedValue);
        if (plaintextValue == null)
          throw new IllegalArgumentException(
              "Plaintext value is null, check the encrypted value is correct: " + keyName);

        propertyValueMap.put(
            keyName, new DefaultPropertyValue(entry.getValue().getPropertyType(), plaintextValue));
      }

    } finally {
      encryptedPropertyValueMap.clear();
    }
  }

  /**
   * Sets the property to the value specified and automatically decrypts if it is a sensitive field.
   *
   * @param configurableProperty the property to set
   * @param value the value (encrypted if sensitive), NOTE that it gets stored in properties
   *     decrypted.
   */
  public void set(Class<? extends ConfigurableProperty> configurableProperty, final String value) {
    if (value == null) return;

    if (isSensitive(configurableProperty)) setSensitiveProperty(configurableProperty, value);
    else setProperty(configurableProperty, value);
  }

  protected boolean isSensitive(
      final Class<? extends ConfigurableProperty> configurablePropertyClass) {
    return configurablePropertyClass.isAnnotationPresent(Sensitive.class);
  }

  @Sensitive
  protected void setSensitiveProperty(
      Class<? extends ConfigurableProperty> configurableProperty, final String value) {
    encryptedPropertyValueMap.put(
        configurableProperty,
        new DefaultPropertyValue(getPropertyValueType(configurableProperty), value));
  }

  protected void setProperty(
      final Class<? extends ConfigurableProperty> configurableProperty, final String value) {
    if (PropertyHelper.isOptional(configurableProperty)) {
      propertyValueMap.put(
          configurableProperty,
          new OptionalPropertyValue(
              getPropertyValueType(configurableProperty), Optional.ofNullable(value)));
    } else {
      propertyValueMap.put(
          configurableProperty,
          new DefaultPropertyValue(getPropertyValueType(configurableProperty), value));
    }
  }

  public static Class getPropertyValueType(
      Class<? extends ConfigurableProperty> configurableProperty) {
    if (configurableProperty.isAnnotationPresent(PropertyValueType.class))
      return configurableProperty.getAnnotation(PropertyValueType.class).value();

    return String.class;
  }

  @Sensitive
  protected String decryptProperty(final String propertyKey) {
    return secretService.get(propertyKey);
  }

  @Sensitive
  public String get(final Class<? extends ConfigurableProperty> configurableProperty) {
    final PropertyValue defaultPropertyValue = propertyValueMap.get(configurableProperty);
    if (defaultPropertyValue != null) return defaultPropertyValue.getValue();

    return null;
  }

  public Class type(final Class<? extends ConfigurableProperty> configurableProperty) {
    final PropertyValue defaultPropertyValue = propertyValueMap.get(configurableProperty);
    if (defaultPropertyValue != null) return defaultPropertyValue.getPropertyType();

    return null;
  }
}
