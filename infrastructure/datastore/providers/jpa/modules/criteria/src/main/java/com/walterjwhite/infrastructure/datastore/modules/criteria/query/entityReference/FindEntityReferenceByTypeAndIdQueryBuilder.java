/*
package com.walterjwhite.infrastructure.datastore.modules.criteria.query.entityReference;

import com.walterjwhite.datastore.api.annotation.Supports;
import com.walterjwhite.datastore.api.model.entity.EntityReference;
import com.walterjwhite.datastore.api.model.entity.EntityReference_;
import com.walterjwhite.datastore.query.entityReference.FindEntityReferenceByTypeAndIdQuery;
import com.walterjwhite.infrastructure.datastore.modules.criteria.CriteriaQueryConfiguration;
import com.walterjwhite.infrastructure.datastore.modules.criteria.query.JpaCriteriaQueryBuilder;

@Supports(FindEntityReferenceByTypeAndIdQuery.class)
public class FindEntityReferenceByTypeAndIdQueryBuilder
    extends JpaCriteriaQueryBuilder<
        EntityReference, EntityReference, FindEntityReferenceByTypeAndIdQuery> {

  protected Predicate buildPredicate(
      CriteriaBuilder criteriaBuilder,
      CriteriaQueryConfiguration<EntityReference, EntityReference> criteriaQueryConfiguration,
      FindEntityReferenceByTypeAndIdQuery findEntityReferenceByTypeAndIdQueryConfiguration) {
    Predicate entityTypeNamePredicate =
        criteriaBuilder.equal(
            criteriaQueryConfiguration.getRoot().get(EntityReference_.entityType),
            findEntityReferenceByTypeAndIdQueryConfiguration.getEntityType());

    Predicate idPredicate =
        criteriaBuilder.equal(
            criteriaQueryConfiguration.getRoot().get(EntityReference_.entityId),
            findEntityReferenceByTypeAndIdQueryConfiguration.getId());

    return criteriaBuilder.and(entityTypeNamePredicate, idPredicate);
  }
}
*/
