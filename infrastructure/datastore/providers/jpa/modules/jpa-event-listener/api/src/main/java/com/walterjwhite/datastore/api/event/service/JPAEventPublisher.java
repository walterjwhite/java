package com.walterjwhite.datastore.api.event.service;

import com.walterjwhite.datastore.api.event.JPAEvent;

public interface JPAEventPublisher {
  void publish(JPAEvent jpaEvent);
}
