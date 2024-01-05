package com.walterjwhite.infrastructure.datastore.jpa.event.listener.provider.guice;

import com.walterjwhite.datastore.api.event.service.JPAEventPublisher;
import com.walterjwhite.datastore.listener.AbstractJPAEntityListener;
import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;

public class GoogleGuiceJPAEntityListener extends AbstractJPAEntityListener {
  public GoogleGuiceJPAEntityListener() {
    super(
        ApplicationHelper.getApplicationInstance()
            .getInjector()
            .getInstance(JPAEventPublisher.class));
  }
}
