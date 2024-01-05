package com.walterjwhite.property.impl.tgt;

import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.property.api.tgt.PropertyTarget;
import com.walterjwhite.property.impl.AbstractPropertyManager;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import org.reflections.Reflections;

public class PropertyTargetManager extends AbstractPropertyManager<PropertyTarget> {

  public PropertyTargetManager(Reflections reflections, final PropertyManager propertyManager) {
    super(reflections, propertyManager);
  }

  protected Set<Class<? extends PropertyTarget>> getClasses() {
    return reflections.getSubTypesOf(PropertyTarget.class);
  }

  protected void processClass(Class<? extends PropertyTarget> targetClass)
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
          InstantiationException {
    final PropertyTarget propertyTarget =
        targetClass.getConstructor(PropertyManager.class).newInstance(propertyManager);
    propertyTarget.set();
  }
}
