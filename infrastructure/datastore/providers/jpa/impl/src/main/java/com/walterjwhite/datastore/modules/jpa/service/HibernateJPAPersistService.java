package com.walterjwhite.datastore.modules.jpa.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.reflections.Reflections;

/** This will conflict with the generic JPA service */
@Singleton
public class HibernateJPAPersistService extends AbstractJPAPersistService {
  /** This is required to use the SLF4J logging wrapper. */
  static {
    System.setProperty("org.jboss.logging.provider", "slf4j");
  }

  @Inject
  public HibernateJPAPersistService(Reflections reflections) {
    super(reflections);
  }
}
