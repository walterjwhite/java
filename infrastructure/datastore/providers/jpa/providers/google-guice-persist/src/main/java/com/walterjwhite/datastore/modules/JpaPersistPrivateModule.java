package com.walterjwhite.datastore.modules;

import com.google.inject.PrivateModule;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.UnitOfWork;
import com.google.inject.persist.jpa.JpaPersistModule;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Properties;
import lombok.RequiredArgsConstructor;

// This was built in order to support multiple Persistence Units; however,
// I seem unable to easily support multiple PU's where the primary can be the default without any
// annotation / qualifier
@Deprecated
@RequiredArgsConstructor
class JpaPersistPrivateModule extends PrivateModule {
  protected final String persistenceUnitName;
  protected final Properties properties;
  protected final Class<? extends Annotation> qualifier;

  public JpaPersistPrivateModule(
      final String persistenceUnitName, final Class<? extends Annotation> qualifier) {
    this(persistenceUnitName, new Properties(), qualifier);
  }

  @Override
  protected void configure() {
    install(new JpaPersistModule(persistenceUnitName).properties(properties));
    rebind(
        qualifier,
        EntityManagerFactory.class,
        EntityManager.class,
        PersistService.class,
        UnitOfWork.class);
    doConfigure();
  }

  private void rebind(Class<? extends Annotation> qualifier, Class<?>... classes) {
    Arrays.stream(classes).forEach(clazz -> rebind(qualifier, clazz));
  }

  private <T> void rebind(Class<? extends Annotation> qualifier, Class<T> clazz) {
    bind(clazz).annotatedWith(qualifier).toProvider(binder().getProvider(clazz));
    expose(clazz).annotatedWith(qualifier);
  }

  /** bind your interfaces and classes as well as concrete ones that use JPA classes explicitly */
  protected void doConfigure() {
    // write your bindings in your subclasses
    // bindConcreteClassWithQualifier(MyTableService.class);
    // ...
  }

  /** binds and exposes a concrete class with an annotation */
  protected <T> void bindConcreteClassWithQualifier(Class<T> clazz) {
    bind(clazz).annotatedWith(qualifier).to(clazz);
    expose(clazz).annotatedWith(qualifier);
  }

  /** binds and exposes a concrete class without any annotation */
  protected void bindConcreteClass(Class<?> clazz) {
    bind(clazz);
    expose(clazz);
  }
}
