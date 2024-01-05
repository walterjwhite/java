package com.walterjwhite.datastore.api.repository;

public interface QueryBuilderResolver {
  QueryBuilder getBuilder(QueryConfiguration queryConfiguration);
}
