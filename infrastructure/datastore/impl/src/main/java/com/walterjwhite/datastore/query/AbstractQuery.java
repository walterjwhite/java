package com.walterjwhite.datastore.query;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.model.entity.Entity;
import com.walterjwhite.datastore.api.repository.QueryConfiguration;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
// @Data
@ToString(doNotUseGetters = true)
@Getter
// @ToString(doNotUseGetters=true)(callSuper = true)
public abstract class AbstractQuery<EntityType extends Entity, ResultType extends Object>
    extends AbstractEntity implements QueryConfiguration<EntityType, ResultType> {
  protected final int offset;
  protected final int recordCount;
  protected final Class<EntityType> entityTypeClass;
  protected final Class<ResultType> resultTypeClass;
  protected final boolean construct;
  // ms
  protected final int queryTimeout;
  protected final List<Sort> sorts;

  public AbstractQuery(
      int offset,
      int recordCount,
      Class<EntityType> entityTypeClass,
      Class<ResultType> resultTypeClass,
      boolean construct) {
    this.offset = offset;
    this.recordCount = recordCount;
    this.entityTypeClass = entityTypeClass;
    this.resultTypeClass = resultTypeClass;
    this.construct = construct;
    this.queryTimeout = -1;
    this.sorts = null;
  }
}
