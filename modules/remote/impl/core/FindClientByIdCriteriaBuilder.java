package com.walterjwhite.remote.impl.query;

import com.walterjwhite.infrastructure.datastore.modules.criteria.CriteriaQueryConfiguration;
import com.walterjwhite.infrastructure.datastore.modules.criteria.query.JpaCriteriaQueryBuilder;
import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.remote.api.model.Client_;

/** This is the Criteria-specific implementation to handle the FindClientByIdQueryConfiguration */
public class FindClientByIdCriteriaBuilder
    extends JpaCriteriaQueryBuilder<Client, Client, FindClientByIdQueryConfiguration> {

  protected Predicate buildPredicate(
      CriteriaBuilder criteriaBuilder,
      CriteriaQueryConfiguration<Client, Client> criteriaQueryConfiguration,
      final FindClientByIdQueryConfiguration findClientByIdQueryConfiguration) {
    return criteriaBuilder.equal(
        criteriaQueryConfiguration.getRoot().get(Client_.id),
        findClientByIdQueryConfiguration.getEntityId());
  }
}
