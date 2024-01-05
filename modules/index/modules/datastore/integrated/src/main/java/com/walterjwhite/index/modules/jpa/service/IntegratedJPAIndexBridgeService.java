package com.walterjwhite.index.modules.jpa.service;

import com.walterjwhite.datastore.api.repository.Repository;
import jakarta.inject.Inject;

/**
 * @deprecated This class should live in the index project as a JPA module
 */
public class IntegratedJPAIndexBridgeService extends AbstractJPAIndexBridgeService {
  @Inject
  public IntegratedJPAIndexBridgeService(Repository repository) {
    super(repository);
  }
}
