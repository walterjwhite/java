package com.walterjwhite.examples.identity;

import com.google.inject.AbstractModule;
import com.walterjwhite.encryption.modules.guice.EncryptionModule;
import com.walterjwhite.infrastructure.inject.providers.guice.GuiceApplicationModule;

public class IdentityExampleGuiceModule extends AbstractModule implements GuiceApplicationModule {

  @Override
  protected void configure() {
    install(new EncryptionModule());


    bind(EntityCreator.class);
  }
}
