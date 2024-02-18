package com.walterjwhite.datastore.jdo.repository;

import jakarta.inject.Provider;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

public class JDOPersistenceManagerProvider implements Provider<PersistenceManager> {

  @Override
  public PersistenceManager get() {



    
    final PersistenceManagerFactory persistenceManagerFactory =
        JDOHelper.getPersistenceManagerFactory("default");

    PersistenceManager persistenceManager = persistenceManagerFactory.getPersistenceManager();



    return persistenceManager;
  }
}
