package com.walterjwhite.infrastructure.datastore.modules.criteria;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CriteriaQueryConfiguration<
    EntityType extends AbstractEntity, ResultType extends /*Serializable*/ Object> {
  protected final CriteriaQuery<ResultType> criteriaQuery;
  protected final Root<EntityType> root;
}
