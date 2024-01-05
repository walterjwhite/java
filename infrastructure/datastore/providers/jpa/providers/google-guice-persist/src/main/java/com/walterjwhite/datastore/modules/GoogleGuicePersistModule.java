package com.walterjwhite.datastore.modules;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.walterjwhite.datastore.GoogleGuicePersistService;

public class GoogleGuicePersistModule extends AbstractModule {
  public static final String jpaUnitName = "primary";

  @Override
  protected void configure() {
    install(new JpaPersistModule(jpaUnitName));
    install(new JavaxTransactionalModule());

    bind(GoogleGuicePersistService.class).asEagerSingleton();

    //    bindInterceptor(Matchers.any(), Matchers.annotatedWith(OnJPAAction.class),
    //            new AuditingRepositoryInterceptor());
  }
}
