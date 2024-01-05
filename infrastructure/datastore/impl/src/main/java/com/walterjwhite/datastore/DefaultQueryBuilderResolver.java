package com.walterjwhite.datastore;

import com.walterjwhite.datastore.api.annotation.Supports;
import com.walterjwhite.datastore.api.repository.QueryBuilder;
import com.walterjwhite.datastore.api.repository.QueryBuilderResolver;
import com.walterjwhite.datastore.api.repository.QueryConfiguration;
import jakarta.inject.Inject;
import org.reflections.Reflections;

public class DefaultQueryBuilderResolver implements QueryBuilderResolver {
  protected final Reflections reflections;

  @Inject
  public DefaultQueryBuilderResolver(Reflections reflections) {
    this.reflections = reflections;
  }

  public QueryBuilder getBuilder(QueryConfiguration queryConfiguration) {
    for (final Class queryBuilderClass : reflections.getTypesAnnotatedWith(Supports.class)) {
      if (queryConfiguration.getClass().equals(getSupports(queryBuilderClass).value())) {
        try {
          return (QueryBuilder) queryBuilderClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
          throw new RuntimeException("Improperly configured.");
        }
      }
    }

    return null;
  }

  protected Supports getSupports(final Class queryBuilderClass) {
    return (Supports) queryBuilderClass.getAnnotation(Supports.class);
  }
}
