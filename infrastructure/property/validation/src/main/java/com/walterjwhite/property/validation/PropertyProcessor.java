package com.walterjwhite.property.validation;

import com.google.auto.service.AutoService;
import com.walterjwhite.property.api.annotation.Property;
import com.walterjwhite.property.api.annotation.PropertyValueType;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

@SupportedAnnotationTypes("com.walterjwhite.property.impl.annotation.Property")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
// @NoArgsConstructor
public class PropertyProcessor extends AbstractProcessor {

  @Override
  public boolean process(
      final Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
    for (final Element element : roundEnvironment.getElementsAnnotatedWith(Property.class)) {
      process(element);
    }

    return false;
  }

  protected void process(final Element element) {
    if (element == null) {
      return;
    }

    final Class[] propertyTypes = getPropertyType(element);
    if (!isA(element, propertyTypes[1]))
      throw new PropertyUsageException(
          new Attribute(propertyTypes[0].getName(), propertyTypes[1].getName()),
          new UsedAttribute(
              element.getSimpleName().toString(),
              element.asType().toString(),
              element.getEnclosingElement().getSimpleName().toString(),
              element.getEnclosingElement().asType().toString()));
  }

  private static boolean isA(final Element element, final Class typeClass) {
    return typeClass.getName().equals(element.asType().toString());
  }

  protected Class[] getPropertyType(final Element element) {
    final Class<? extends ConfigurableProperty> configurablePropertyClass =
        getConfigurablePropertyClass(element);
    if (configurablePropertyClass.isAnnotationPresent(PropertyValueType.class)) {
      return new Class[] {
        configurablePropertyClass,
        configurablePropertyClass.getAnnotation(PropertyValueType.class).value()
      };
    }

    return new Class[] {configurablePropertyClass, String.class};
  }

  protected Class<? extends ConfigurableProperty> getConfigurablePropertyClass(
      final Element element) {
    final Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry =
        element.getAnnotationMirrors().get(0).getElementValues().entrySet().iterator().next();
    final Object value = entry.getValue().getValue();

    try {
      return (Class<? extends ConfigurableProperty>) Class.forName(value.toString());
    } catch (ClassNotFoundException e) {
      throw new Error("Unable to determine class from: " + value, e);
    }
  }
}
