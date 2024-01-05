package com.walterjwhite.email.organization.plugins.index;

import com.walterjwhite.email.api.model.Email;
import com.walterjwhite.email.api.model.PrivateEmailAccount;
import com.walterjwhite.email.organization.api.Action;
import com.walterjwhite.email.organization.api.configuration.rule.EmailMatcherRule;
import com.walterjwhite.index.api.service.IndexMaintenanceService;
import com.walterjwhite.index.api.service.IndexService;
import com.walterjwhite.infrastructure.inject.core.service.StartupAware;
import java.io.IOException;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IndexAction implements Action, StartupAware {

  protected final IndexService indexService;
  protected final IndexMaintenanceService indexMaintenanceService;

  @Inject
  public IndexAction(
      final IndexService indexService, IndexMaintenanceService indexMaintenanceService) {
    this.indexService = indexService;
    this.indexMaintenanceService = indexMaintenanceService;
  }

  @Override
  public void execute(
      String folderName,
      PrivateEmailAccount privateEmailAccount,
      EmailMatcherRule emailMatcherRule,
      Email email)
      throws Exception {

    indexService.index(email);
  }

  @Override
  public void startup() {
    try {
      indexMaintenanceService.create(Email.class);
    } catch (IOException | RuntimeException e) {
      LOGGER.warn("index must already exist", e);
    }
  }

  @Override
  public void close() {}
}
