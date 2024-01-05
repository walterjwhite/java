package com.walterjwhite.infrastructure.datastore.modules.criteria.query;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.repository.QueryBuilder;
import com.walterjwhite.datastore.api.repository.QueryConfiguration;
import com.walterjwhite.infrastructure.datastore.modules.criteria.CriteriaQueryConfiguration;
import java.util.List;
import java.util.stream.Stream;

public abstract class JpaCriteriaQueryBuilder<
        EntityType extends AbstractEntity,
        // the intent here was to store references on references, but is that worth it?
        ResultType extends /*Serializable*/ Object,
        QueryConfigurationType extends QueryConfiguration<EntityType, ResultType>>
    implements QueryBuilder<EntityType, ResultType, QueryConfigurationType, EntityManager> {

  public ResultType get(EntityManager entityManager, QueryConfigurationType query) {
    final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

    final CriteriaQueryConfiguration<EntityType, ResultType> criteriaQueryConfiguration =
        getCriteriaQuery(criteriaBuilder, query);

    final Predicate predicate = buildPredicate(criteriaBuilder, criteriaQueryConfiguration, query);
    if (predicate != null) criteriaQueryConfiguration.getCriteriaQuery().where(predicate);

    final TypedQuery<ResultType> typedQuery =
        entityManager.createQuery(criteriaQueryConfiguration.getCriteriaQuery());

    configureResultSet(query, typedQuery);

    return (ResultType) getResult(criteriaQueryConfiguration.getCriteriaQuery(), typedQuery);
  }

  protected CriteriaQueryConfiguration getCriteriaQuery(
      CriteriaBuilder criteriaBuilder, QueryConfigurationType query) {
    CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(query.getEntityTypeClass());
    Root<EntityType> root = criteriaQuery.from(query.getEntityTypeClass());

    return new CriteriaQueryConfiguration(criteriaQuery, root);
  }

  protected void configureResultSet(
      final QueryConfigurationType query, final TypedQuery typedQuery) {
    setRecordCount(query, typedQuery);
    setOffset(query, typedQuery);
  }

  protected void setRecordCount(final QueryConfigurationType query, final TypedQuery typedQuery) {
    if (query.getRecordCount() > 0) typedQuery.setMaxResults(query.getRecordCount());
  }

  protected void setOffset(final QueryConfigurationType query, final TypedQuery typedQuery) {
    if (query.getOffset() >= 0) typedQuery.setFirstResult(query.getOffset());
  }

  protected Object getResult(final CriteriaQuery criteriaQuery, final TypedQuery typedQuery) {
    if (Stream.class.equals(criteriaQuery.getResultType())) return typedQuery.getResultStream();
    if (List.class.equals(criteriaQuery.getResultType())) return typedQuery.getResultList();

    return typedQuery.getSingleResult();
  }

  protected abstract Predicate buildPredicate(
      CriteriaBuilder criteriaBuilder,
      CriteriaQueryConfiguration<EntityType, ResultType> criteriaQueryConfiguration,
      QueryConfigurationType queryConfiguration);
}
