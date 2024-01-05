package com.walterjwhite.infrastructure.datastore.modules.jpa.ddl;

import com.google.inject.AbstractModule;
import com.walterjwhite.infrastructure.datastore.modules.jpa.ddl.service.GenerateDDLService;

public class GenerateDDLModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(GenerateDDLService.class);
  }
}
