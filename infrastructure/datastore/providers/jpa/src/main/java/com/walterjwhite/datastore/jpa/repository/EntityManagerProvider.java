package com.walterjwhite.datastore.jpa.repository;

import com.walterjwhite.datastore.jpa.PersistenceUnitName;
import com.walterjwhite.property.api.annotation.Property;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Getter;

@Getter
public class EntityManagerProvider implements Provider<EntityManager> {
  protected final String persistenceUnitName;
  protected final EntityManagerFactory entityManagerFactory;

  @Inject
  public EntityManagerProvider(
      @Property(PersistenceUnitName.class) final String persistenceUnitName) {
    this.persistenceUnitName = persistenceUnitName;
    this.entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
  }

  @Override
  public EntityManager get() {
    return entityManagerFactory.createEntityManager();
  }
}
