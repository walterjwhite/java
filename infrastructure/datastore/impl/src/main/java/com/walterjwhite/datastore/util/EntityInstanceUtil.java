package com.walterjwhite.datastore.util;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.infrastructure.inject.core.enumeration.ProviderType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EntityInstanceUtil {
  public static <EntityType extends AbstractEntity> EntityType instantiateNewObject(
      final Class<EntityType> entityType, final Object... arguments) {
    return ProviderType.Self.get(entityType, arguments);
  }
}
