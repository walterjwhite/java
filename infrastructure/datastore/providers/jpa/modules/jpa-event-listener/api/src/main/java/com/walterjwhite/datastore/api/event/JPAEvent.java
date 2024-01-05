package com.walterjwhite.datastore.api.event;

import com.walterjwhite.datastore.api.event.enumeration.JPAActionType;
import com.walterjwhite.datastore.api.event.enumeration.JPAExecutionTimeType;
import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Deprecated
public class JPAEvent {
  protected final AbstractEntity entity;
  protected final JPAActionType jpaActionType;
  protected final JPAExecutionTimeType jpaExecutionTimeType;
}
