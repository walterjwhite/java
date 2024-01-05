package com.walterjwhite.infrastructure.datastore.modules.criteria.query.tag;

import com.walterjwhite.datastore.api.model.entity.Tag;
import com.walterjwhite.datastore.api.model.entity.Tag_;
import com.walterjwhite.infrastructure.datastore.modules.criteria.CriteriaQueryConfiguration;

public class FindTagByNameQueryBuilder {
  private static Predicate buildPredicate(
      CriteriaBuilder criteriaBuilder,
      CriteriaQueryConfiguration<Tag, Tag> criteriaQueryConfiguration,
      String name) {
    return criteriaBuilder.equal(criteriaQueryConfiguration.getRoot().get(Tag_.name), name);
  }
}
