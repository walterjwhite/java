package com.walterjwhite.email.javamail;

import jakarta.inject.Inject;
import jakarta.inject.Provider;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Store;

public class StoreProvider implements Provider<Store> {
  protected final Session session;

  @Inject
  public StoreProvider(Session session) {
    this.session = session;
  }

  @Override
  public Store get() {
    try {
      return JavaMailUtils.getStore(session);
    } catch (MessagingException e) {
      throw new RuntimeException("Error getting Store", e);
    }
  }
}
