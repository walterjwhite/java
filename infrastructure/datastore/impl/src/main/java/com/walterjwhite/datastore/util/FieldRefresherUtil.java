package com.walterjwhite.datastore.util;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.datastore.enumeration.FieldRefresher;
import java.lang.reflect.Field;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FieldRefresherUtil {
  public static AbstractEntity refresh(
      Repository repository, AbstractEntity currentEntity, final boolean computeId)
      throws IllegalAccessException {
    if (currentEntity == null) {
      return null;
    }

    return refresh(
        repository, currentEntity, currentEntity.getClass(), currentEntity.getClass(), computeId);
  }

  public static AbstractEntity refresh(
      Repository repository,
      AbstractEntity currentEntity,
      final Class fromClass,
      final Class entityClass,
      final boolean computeId)
      throws IllegalAccessException {
    if (computeId) {
      throw new UnsupportedOperationException("This is no longer supported since migrating to JDO");
    }

    if (isManaged(currentEntity)) {
      final AbstractEntity currentManagedEntity =
          repository.findById(currentEntity.getClass(), currentEntity.getId());

      if (currentManagedEntity != null) {
        return currentManagedEntity;
      }
    }

    // attempt to update the entity fields if those are perhaps FK relationships ...
    refreshEntityFields(repository, currentEntity, fromClass, entityClass, computeId);
    return currentEntity;
  }

  private static boolean isManaged(final AbstractEntity currentEntity) {
    return (currentEntity != null && currentEntity.getId() > 0);
  }

  public static AbstractEntity refresh(Repository repository, AbstractEntity entity)
      throws IllegalAccessException {
    return refresh(repository, entity, false);
  }

  public static void refreshEntityFields(
      Repository repository,
      AbstractEntity entity,
      final Class fromClass,
      final Class entityClass,
      final boolean computeId)
      throws IllegalAccessException {
    refreshFields(repository, entity, fromClass, entityClass, computeId);
    refreshSuperclassFields(repository, entity, fromClass, entityClass, computeId);

    // is this needed here?
    // if (computeId) entity.onPrePersist();
  }

  private static void refreshSuperclassFields(
      Repository repository,
      AbstractEntity entity,
      final Class fromClass,
      final Class entityClass,
      final boolean computeId)
      throws IllegalAccessException {
    if (AbstractEntity.class.isAssignableFrom(entityClass.getSuperclass())) {
      refreshEntityFields(repository, entity, fromClass, entityClass.getSuperclass(), computeId);
    }
  }

  private static void refreshFields(
      Repository repository,
      AbstractEntity entity,
      final Class fromClass,
      final Class entityClass,
      final boolean computeId)
      throws IllegalAccessException {
    if (AbstractEntity.class.isAssignableFrom(entityClass)) {
      for (final Field field : entityClass.getDeclaredFields()) {
        refreshField(repository, entity, fromClass, entityClass, field, computeId);
      }
    }
  }

  private static boolean refreshField(
      Repository repository,
      AbstractEntity entity,
      final Class fromClass,
      final Class entityClass,
      final Field field,
      final boolean computeId)
      throws IllegalAccessException {

    final boolean wasAccessible = field.canAccess(entity);
    try {
      field.setAccessible(true);

      if (hasData(entity, field)) {
        if (!isCycle(fromClass, field.getType())) {
          return doRefreshField(repository, entity, fromClass, entityClass, field, computeId);
        }
      }

      return false;
    } finally {
      field.setAccessible(wasAccessible);
    }
  }

  private static boolean hasData(AbstractEntity entity, final Field field)
      throws IllegalAccessException {
    return field.get(entity) != null;
  }

  private static boolean isCycle(final Class fromClass, final Class entityType) {
    return fromClass != null && fromClass.equals(entityType);
  }

  private static boolean doRefreshField(
      Repository repository,
      AbstractEntity entity,
      final Class fromClass,
      final Class entityClass,
      final Field field,
      final boolean computeId)
      throws IllegalAccessException {
    final FieldRefresher fieldRefresher = FieldRefresher.get(field.getType());
    if (fieldRefresher == null) {
      return false;
    }

    /*if (fieldRefresher.isSupported(entity, field))*/ {
      final Object result =
          fieldRefresher.refresh(repository, entity, fromClass, entityClass, field, computeId);

      if (result != null) {
        field.set(entity, result);

        return true;
      }

      // this step is unnecessary
      // field itself may not be managed, but check fields within that entity
      //      refreshEntityFields(
      //          entityManager, (AbstractEntity) field.get(entity), fromClass, field.getType(),
      // computeId);

      return false;
    }

    // return false;
  }
}
