package com.walterjwhite.infrastructure.datastore.modules.criteria.query.entityType;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.model.entity.EntityContainerType;
import com.walterjwhite.datastore.api.model.entity.EntityType;
import com.walterjwhite.datastore.api.model.entity.EntityType_;
import com.walterjwhite.infrastructure.datastore.modules.criteria.CriteriaQueryConfiguration;

public class FindEntityTypeByEntityClassTypeQueryBuilder {
  private static Predicate buildPredicate(
      CriteriaBuilder criteriaBuilder,
      CriteriaQueryConfiguration<EntityType, EntityType> criteriaQueryConfiguration,
      Class<? extends AbstractEntity> entityClassType) {
    Predicate entityTypeNameCondition =
        criteriaBuilder.equal(
            criteriaQueryConfiguration.getRoot().get(EntityType_.name), entityClassType.getName());

    Predicate entityContainerTypeCondition =
        criteriaBuilder.equal(
            criteriaQueryConfiguration.getRoot().get(EntityType_.entityContainerType),
            EntityContainerType.Database);

    criteriaQueryConfiguration.getCriteriaQuery().where(entityTypeNameCondition);

    return criteriaBuilder.and(entityTypeNameCondition, entityContainerTypeCondition);
  }
}
