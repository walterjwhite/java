package com.walterjwhite.serialization.modules.snakeyaml;

import com.walterjwhite.logging.annotation.Sensitive;
import com.walterjwhite.serialization.api.annotation.PrivateField;
import com.walterjwhite.serialization.modules.snakeyaml.types.DurationRepresenter;
import java.lang.reflect.Field;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

public class MessageRepresenter extends Representer {
  public MessageRepresenter() {
    super(new DumperOptions());

    this.representers.put(Duration.class, new DurationRepresenter());
  }

  @Override
  protected Set<Property> getProperties(Class<?> type) {
    final Set<Property> properties = super.getProperties(type);
    final Set<Property> safeProperties = new HashSet<>();

    for (final Property property : properties) {
      if (!isFieldPrivate(type, property)) {
        safeProperties.add(property);
      }
    }

    return (safeProperties);
  }

  private static boolean isFieldPrivate(final Class<?> type, final Property property) {

    try {
      final Field field = type.getDeclaredField(property.getName());
      return (field.isAnnotationPresent(PrivateField.class));
    } catch (NoSuchFieldException e) {
      return (isFieldPrivate(type.getSuperclass(), property));
    }
  }


  @Override
  protected NodeTuple representJavaBeanProperty(
      @Sensitive Object javaBean, Property property, Object propertyValue, Tag customTag) {
    if (propertyValue == null) {
      return null;
    }

    return super.representJavaBeanProperty(javaBean, property, propertyValue, customTag);
  }
}
