package com.walterjwhite.encryption.guice;

import com.google.inject.Binder;
import com.walterjwhite.encryption.modules.guice.EncryptionModule;
import com.walterjwhite.infrastructure.inject.providers.guice.GuiceApplicationModule;

public class CLEncryptionModule implements GuiceApplicationModule {
  @Override
  public void configure(final Binder binder) {
    bindModules(binder);
  }

  protected void bindModules(final Binder binder) {
    binder.install(new EncryptionModule());
  }
}
