package com.walterjwhite.infrastructure.datastore.modules.criteria.query;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.query.AbstractQuery;

/**
 * 1. count # of records matching criteria (n records) 2. select a random # in between (between 0
 * and n), x 3. get first record with offset of x matching the original query
 */
public abstract class AbstractRandomQuery<EntityType extends AbstractEntity>
    extends AbstractQuery<EntityType, Long> {
  public AbstractRandomQuery(Class<EntityType> entityTypeClass) {
    super(0, 1, entityTypeClass, Long.class, false);
  }

  //  @Override
  //  public void doGet(CriteriaQuery<Long> criteriaQuery) {
  //    criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(entityTypeClass)));
  //  }

  //    public EntityType findRandom(
  //            Class<? extends AbstractEntity> entityClass, Predicate... conditions) {
  //        return (get(entityClass, new Random().nextInt(count(entityClass, conditions)), 1,
  // conditions)
  //                .get(0));
  //    }
}
