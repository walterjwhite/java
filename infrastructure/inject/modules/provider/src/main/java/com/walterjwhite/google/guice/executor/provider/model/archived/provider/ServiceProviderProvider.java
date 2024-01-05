package com.walterjwhite.google.guice.executor.provider.model.archived.provider;

import com.walterjwhite.datastore.api.repository.Repository;
import jakarta.inject.Inject;

class ServiceProviderProvider {
  protected final Repository repository;

  @Inject
  ServiceProviderProvider(Repository repository) {

    this.repository = repository;
  }

  // find the default provider for a given service public interface classname
  //    ServiceProviderType get(){
  //        repository.findAll()
  //    }
}
