package com.walterjwhite.datastore.listener;

import com.walterjwhite.datastore.api.event.JPAEvent;
import com.walterjwhite.datastore.api.event.enumeration.JPAActionType;
import com.walterjwhite.datastore.api.event.enumeration.JPAExecutionTimeType;
import com.walterjwhite.datastore.api.event.service.JPAEventPublisher;
import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import lombok.RequiredArgsConstructor;

/*
 * Simple JPA listener that listens for JPA events and republishes them on EventBus. EventBus
 * registrations *MUST* happen before this.
 */
@RequiredArgsConstructor
public abstract class AbstractJPAEntityListener {
  protected final JPAEventPublisher jpaEventPublisher;

  @PrePersist
  public void onPrePersist(Object o) {
    fireEvent(new JPAEvent((AbstractEntity) o, JPAActionType.Persist, JPAExecutionTimeType.Pre));
  }

  @PreUpdate
  public void onPreUpdate(Object o) {
    fireEvent(new JPAEvent((AbstractEntity) o, JPAActionType.Update, JPAExecutionTimeType.Pre));
  }

  @PreRemove
  public void onPreRemove(Object o) {
    fireEvent(new JPAEvent((AbstractEntity) o, JPAActionType.Remove, JPAExecutionTimeType.Pre));
  }

  @PostPersist
  public void onPostPersist(Object o) {
    fireEvent(new JPAEvent((AbstractEntity) o, JPAActionType.Persist, JPAExecutionTimeType.Post));
  }

  @PostUpdate
  public void onPostUpdate(Object o) {
    fireEvent(new JPAEvent((AbstractEntity) o, JPAActionType.Update, JPAExecutionTimeType.Post));
  }

  @PostRemove
  public void onPostRemove(Object o) {
    fireEvent(new JPAEvent((AbstractEntity) o, JPAActionType.Remove, JPAExecutionTimeType.Post));
  }

  protected void fireEvent(JPAEvent event) {
    jpaEventPublisher.publish(event);
  }
}
