package com.walterjwhite.infrastructure.datastore.modules.criteria;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.repository.Repository;
import java.util.Iterator;
import java.util.NoSuchElementException;
import jakarta.inject.Provider;

// filtering resultset
public class ResultsetIterator<EntityType extends AbstractEntity> implements Iterator<EntityType> {
  protected final CriteriaQuery<EntityType> criteriaQuery;
  protected final int count;
  protected final Provider<Repository> repositoryProvider;

  protected int index = 0;

  public ResultsetIterator(
      CriteriaQuery<EntityType> criteriaQuery, Provider<Repository> repositoryProvider) {

    this.criteriaQuery = criteriaQuery;
    this.repositoryProvider = repositoryProvider;
    this.count = -1;
    // repositoryProvider.get().count(criteriaQuery);
  }

  @Override
  public boolean hasNext() {
    return index < count - 1;
  }

  @Override
  public EntityType next() {
    if (!hasNext()) throw new NoSuchElementException("No more elements available.");

    // return (EntityType) repositoryProvider.get().get(criteriaQuery, index++);
    return null;
  }
}
