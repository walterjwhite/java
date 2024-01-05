package com.walterjwhite.datastore.enumeration;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.datastore.util.FieldRefresherUtil;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public enum FieldRefresher {
  SingleType(AbstractEntity.class) {
    @Override
    protected boolean isSupported(AbstractEntity entity, final Field field) {
      return true;
    }

    @Override
    public Object refresh(
        Repository repository,
        AbstractEntity entity,
        final Class fromClass,
        final Class entityClass,
        final Field field,
        final boolean computeId)
        throws IllegalAccessException {
      return FieldRefresherUtil.refresh(
          repository, (AbstractEntity) field.get(entity), fromClass, field.getType(), computeId);
    }

    @Override
    protected Class<? extends AbstractEntity> getType(Field field, AbstractEntity entity) {
      return (Class<? extends AbstractEntity>) field.getType();
    }
  },
  IterableType(Iterable.class) {
    @Override
    protected boolean isSupported(AbstractEntity entity, final Field field) {
      try {
        return (((Iterable) field.get(entity)).iterator().hasNext()
            && AbstractEntity.class.isAssignableFrom(
                ((Iterable) field.get(entity)).iterator().next().getClass()));
      } catch (IllegalAccessException e) {
        throw new RuntimeException("Not properly configured", e);
      }
    }

    @Override
    public Object refresh(
        Repository repository,
        AbstractEntity entity,
        final Class fromClass,
        final Class entityClass,
        final Field field,
        final boolean computeId)
        throws IllegalAccessException {
      final Collection<AbstractEntity> refreshEntities =
          (Collection<AbstractEntity>) field.get(entity);
      final List<AbstractEntity> refreshedEntities = new ArrayList<>();

      for (final AbstractEntity entityItem : refreshEntities) {
        final AbstractEntity refreshedEntity =
            FieldRefresherUtil.refresh(
                repository, entityItem, fromClass, entityItem.getClass(), computeId);
        if (refreshedEntity != null) {
          refreshedEntities.add(refreshedEntity);
        } else refreshedEntities.add(entityItem);
      }

      refreshEntities.clear();
      refreshEntities.addAll(refreshedEntities);

      return (refreshEntities);
    }

    @Override
    protected Class<? extends AbstractEntity> getType(Field field, AbstractEntity entity)
        throws IllegalAccessException {
      if (field.get(entity) != null && ((Iterable) field.get(entity)).iterator().hasNext())
        return (Class<? extends AbstractEntity>)
            ((Iterable) field.get(entity)).iterator().next().getClass();

      return (null);
    }
  };

  final Class supportedType;

  FieldRefresher(Class supportedType) {
    this.supportedType = supportedType;
  }

  protected abstract boolean isSupported(AbstractEntity entity, final Field field);

  public abstract Object refresh(
      Repository repository,
      AbstractEntity entity,
      final Class fromClass,
      final Class entityClass,
      final Field field,
      final boolean computeId)
      throws IllegalAccessException;

  protected abstract Class<? extends AbstractEntity> getType(
      final Field field, AbstractEntity entity) throws IllegalAccessException;

  public static FieldRefresher get(final Class fieldClass) {
    for (FieldRefresher fieldRefresher : FieldRefresher.values()) {
      if (fieldRefresher.supportedType.isAssignableFrom(fieldClass)) return fieldRefresher;
    }

    return null;
  }
}
