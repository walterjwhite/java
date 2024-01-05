package com.walterjwhite.datastore.modules.jpa.service;

import com.walterjwhite.datastore.api.PersistenceShutdownService;
import com.walterjwhite.datastore.api.PersistenceStartupService;
import com.walterjwhite.infrastructure.inject.core.Injector;
import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
import com.walterjwhite.infrastructure.inject.core.service.StartupAware;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.reflections.Reflections;

/**
 * NOTE: this was made abstract so as to not conflict with the Hibernate-specific implementation. If
 * a more agnostic implementation is required, split the hibernate implementation out and create a
 * concrete subclass of this.
 */
@Getter
@RequiredArgsConstructor
public abstract class AbstractJPAPersistService implements StartupAware {
  protected final Reflections reflections;
  protected final Injector injector = ApplicationHelper.getApplicationInstance().getInjector();

  protected void runPersistStartupServices() throws Exception {
    for (final Class<? extends PersistenceStartupService> persistenceStartupServiceClass :
        reflections.getSubTypesOf(PersistenceStartupService.class)) {
      final PersistenceStartupService persistenceStartupService =
          injector.getInstance(persistenceStartupServiceClass);
      persistenceStartupService.start();
    }
  }

  protected void runPersistShutdownServices() throws Exception {
    for (final Class<? extends PersistenceShutdownService> persistenceShutdownServiceClass :
        reflections.getSubTypesOf(PersistenceShutdownService.class)) {
      final PersistenceShutdownService persistenceShutdownService =
          injector.getInstance(persistenceShutdownServiceClass);
      persistenceShutdownService.stop();
    }
  }

  @Override
  public void startup() throws Exception {
    runPersistStartupServices();
  }

  @Override
  public void close() {
    try {
      runPersistShutdownServices();
    } catch (Exception e) {
      throw new RuntimeException("Error shutting down persistence", e);
    }
  }
}
