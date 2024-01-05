package com.walterjwhite.datastore.api.repository;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;

public interface QueryBuilder<
    EntityType extends AbstractEntity,
    ResultType extends /*Serializable*/ Object,
    QueryConfigurationType extends QueryConfiguration<EntityType, ResultType>,
    DatastoreType> {
  ResultType get(DatastoreType datastoreType, QueryConfigurationType query);
}
