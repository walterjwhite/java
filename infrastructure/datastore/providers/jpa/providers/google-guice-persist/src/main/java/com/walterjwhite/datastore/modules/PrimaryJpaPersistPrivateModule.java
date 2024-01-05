package com.walterjwhite.datastore.modules;

import com.google.inject.PrivateModule;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.walterjwhite.datastore.api.repository.Repository;
import java.util.Properties;

// No longer supporting multiple PUs because I cannot seem to get it working the way I'd like
// @RequiredArgsConstructor
@Deprecated
class PrimaryJpaPersistPrivateModule extends PrivateModule {
  public static final String jpaUnitName = "primary";
  protected final Properties properties = new Properties();

  @Override
  protected void configure() {
    install(new JpaPersistModule(jpaUnitName).properties(properties));
    // rebind(EntityManagerFactory.class, EntityManager.class, PersistService.class,
    // UnitOfWork.class);
    doConfigure();

    // bind(Repository.class).to(PrimaryJpaRepository.class);
    expose(Repository.class);
  }

  //  private void rebind(Class<?>... classes) {
  //    Arrays.stream(classes).forEach(clazz -> rebind(clazz));
  //  }
  //
  //  private <T> void rebind(Class<T> clazz) {
  //    bind(clazz).annotatedWith(Primary.class).toProvider(binder().getProvider(clazz));
  //    expose(clazz);
  //  }

  /** bind your interfaces and classes as well as concrete ones that use JPA classes explicitly */
  protected void doConfigure() {
    // write your bindings in your subclasses
    // bindConcreteClassWithQualifier(MyTableService.class);
    // ...
  }
}
