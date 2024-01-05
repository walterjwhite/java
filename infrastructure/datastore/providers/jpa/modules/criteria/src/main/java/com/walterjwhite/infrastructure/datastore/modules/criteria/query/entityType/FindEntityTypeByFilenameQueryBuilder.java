package com.walterjwhite.infrastructure.datastore.modules.criteria.query.entityType;

import com.walterjwhite.datastore.api.model.entity.EntityContainerType;
import com.walterjwhite.datastore.api.model.entity.EntityType;
import com.walterjwhite.datastore.api.model.entity.EntityType_;
import com.walterjwhite.infrastructure.datastore.modules.criteria.CriteriaQueryConfiguration;

public class FindEntityTypeByFilenameQueryBuilder {
  private static Predicate buildPredicate(
      CriteriaBuilder criteriaBuilder,
      CriteriaQueryConfiguration<EntityType, EntityType> criteriaQueryConfiguration,
      String entityTypeName) {
    Predicate entityTypeNameCondition =
        criteriaBuilder.equal(
            criteriaQueryConfiguration.getRoot().get(EntityType_.name), entityTypeName);

    Predicate entityContainerTypeCondition =
        criteriaBuilder.equal(
            criteriaQueryConfiguration.getRoot().get(EntityType_.entityContainerType),
            EntityContainerType.File);

    criteriaQueryConfiguration.getCriteriaQuery().where(entityTypeNameCondition);

    return criteriaBuilder.and(entityTypeNameCondition, entityContainerTypeCondition);
  }
}
