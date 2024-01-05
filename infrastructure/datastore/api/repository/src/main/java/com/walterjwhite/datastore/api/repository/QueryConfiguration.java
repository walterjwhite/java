package com.walterjwhite.datastore.api.repository;

import com.walterjwhite.datastore.api.model.entity.Entity;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public interface QueryConfiguration<EntityType extends Entity, ResultType extends Object> {
  int getOffset();

  int getRecordCount();

  Class<EntityType> getEntityTypeClass();

  Class<ResultType> getResultTypeClass();

  boolean isConstruct();

  // by default, do not support auto-create
  default Object[] getAutoCreateArguments() {
    final List values = new ArrayList();
    for (final Field field : getClass().getDeclaredFields()) {
      if (isQueryField(field)) {
        values.add(get(field, this));
      }
    }

    return values.toArray();
  }

  static boolean isQueryField(final Field field) {
    if (field.isSynthetic()) return false;

    if (!Modifier.isFinal(field.getModifiers())) return false;

    return !Modifier.isStatic(field.getModifiers());
  }

  static Object get(final Field field, final Object object) {
    final boolean wasAccessible = field.isAccessible();

    try {
      if (!wasAccessible) field.setAccessible(true);

      return field.get(object);
    } catch (IllegalAccessException e) {
      throw new RuntimeException("Unable to get arguments", e);
    } finally {
      if (!wasAccessible) field.setAccessible(wasAccessible);
    }
  }

  default boolean supportsAutoCreate() {
    return getRecordCount() == 1;
  }
}
