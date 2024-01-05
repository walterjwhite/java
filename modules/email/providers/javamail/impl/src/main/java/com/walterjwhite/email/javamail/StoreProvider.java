package com.walterjwhite.email.javamail;

import jakarta.inject.Inject;
import jakarta.inject.Provider;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

// @RequiredArgsConstructor(onConstructor_ = @Inject)
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
