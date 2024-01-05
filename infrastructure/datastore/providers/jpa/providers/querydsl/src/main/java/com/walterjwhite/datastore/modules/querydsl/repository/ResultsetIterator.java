package com.walterjwhite.datastore.modules.querydsl.repository;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.modules.jpa.JpaRepository;
import java.util.Iterator;
import java.util.NoSuchElementException;
import jakarta.inject.Provider;

public class ResultsetIterator<EntityType extends AbstractEntity> implements Iterator<EntityType> {
  //  protected final CriteriaQuery<EntityType> criteriaQuery;
  protected final int count;
  protected final Provider<JpaRepository> repositoryProvider;

  protected int index = 0;

  public ResultsetIterator(
      /*CriteriaQuery<EntityType> criteriaQuery, */ Provider<JpaRepository> repositoryProvider) {

    //    this.criteriaQuery = criteriaQuery;
    this.repositoryProvider = repositoryProvider;
    this.count = /*repositoryProvider.get().count(criteriaQuery);*/ -1;
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
