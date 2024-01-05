package com.walterjwhite.ip.impl;

import com.google.inject.AbstractModule;
import com.walterjwhite.ip.api.service.PublicIPLookupService;
import com.walterjwhite.ip.impl.service.PublicIPLookupServiceProvider;

public class PublicIPLookupModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(PublicIPLookupService.class).toProvider(PublicIPLookupServiceProvider.class);
  }
}
