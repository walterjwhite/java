package com.walterjwhite.property.impl;

import com.walterjwhite.property.api.annotation.Property;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import java.lang.annotation.Annotation;
import jakarta.enterprise.util.AnnotationLiteral;
import lombok.ToString;

@ToString(doNotUseGetters = true)
public class PropertyImpl extends AnnotationLiteral<Property> implements Property {

  private final Class<? extends ConfigurableProperty> value;

  public PropertyImpl(Class<? extends ConfigurableProperty> value) {
    this.value = value;
  }

  @Override
  public Class<? extends ConfigurableProperty> value() {
    return value;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Property)) {
      return false;
    }

    Property other = (Property) obj;
    return value.equals(other.value());
  }

  @Override
  public int hashCode() {
    // This is specified in java.lang.Annotation.
    return (127 * "value".hashCode()) ^ value.hashCode();
  }

  @Override
  public Class<? extends Annotation> annotationType() {
    return Property.class;
  }
}
