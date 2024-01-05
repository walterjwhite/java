package com.walterjwhite.serialization.modules.java;

import com.google.inject.AbstractModule;
import com.walterjwhite.serialization.api.service.SerializationService;

public class NativeSerializationServiceModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(SerializationService.class).to(NativeSerializationService.class);
  }
}
