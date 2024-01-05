package com.walterjwhite.infrastructure.datastore.modules.criteria.query.entityType;

import com.walterjwhite.datastore.api.annotation.Supports;
import com.walterjwhite.datastore.api.model.entity.EntityContainerType;
import com.walterjwhite.datastore.api.model.entity.EntityType;
import com.walterjwhite.datastore.api.model.entity.EntityType_;
import com.walterjwhite.datastore.query.entityType.FindEntityTypeByNameQuery;
import com.walterjwhite.infrastructure.datastore.modules.criteria.CriteriaQueryConfiguration;
import com.walterjwhite.infrastructure.datastore.modules.criteria.query.JpaCriteriaQueryBuilder;

@Supports(FindEntityTypeByNameQuery.class)
public class FindEntityTypeByNameQueryBuilder
    extends JpaCriteriaQueryBuilder<EntityType, EntityType, FindEntityTypeByNameQuery> {

  protected Predicate buildPredicate(
      CriteriaBuilder criteriaBuilder,
      CriteriaQueryConfiguration<EntityType, EntityType> criteriaQueryConfiguration,
      FindEntityTypeByNameQuery findEntityTypeByNameQuery) {
    Predicate entityTypeNamePredicate =
        criteriaBuilder.equal(
            criteriaQueryConfiguration.getRoot().get(EntityType_.name),
            findEntityTypeByNameQuery.getEntityTypeName());

    Predicate idPredicate =
        criteriaBuilder.equal(
            criteriaQueryConfiguration.getRoot().get(EntityType_.entityContainerType),
            EntityContainerType.Database);

    return criteriaBuilder.and(entityTypeNamePredicate, idPredicate);
  }
}
