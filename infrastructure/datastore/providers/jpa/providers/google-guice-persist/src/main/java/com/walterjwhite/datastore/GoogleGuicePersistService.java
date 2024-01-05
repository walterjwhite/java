package com.walterjwhite.datastore;

import com.google.inject.persist.PersistService;
import com.walterjwhite.datastore.api.PersistenceShutdownService;
import com.walterjwhite.datastore.api.PersistenceStartupService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

@Singleton
@Slf4j
public class GoogleGuicePersistService
    implements PersistenceStartupService, PersistenceShutdownService {
  protected final PersistService persistService;
  protected final Reflections reflections;

  // see:
  // https://github.com/nuzayats/guice-persist-two-pu/blob/master/src/test/java/guice/MyModuleTest.java
  @Inject
  public GoogleGuicePersistService(PersistService persistService, Reflections reflections) {

    this.persistService = persistService;
    this.reflections = reflections;

    doStart();
  }

  protected void doStart() {
    persistService.start();
  }

  @Override
  public void start() {
    // persistService.start();
  }

  @Override
  public void stop() {
    doShutdown(persistService);
  }

  protected void doShutdown(PersistService persistService) {
    try {
      persistService.stop();
    } catch (Exception e) {
      LOGGER.warn(String.format("error stopping persistService: %s", persistService), e);
    }
  }
}
