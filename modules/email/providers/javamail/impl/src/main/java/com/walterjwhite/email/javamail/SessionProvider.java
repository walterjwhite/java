package com.walterjwhite.email.javamail;

import com.walterjwhite.email.api.model.PrivateEmailAccount;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import jakarta.mail.Session;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class SessionProvider implements Provider<Session> {
  protected final PrivateEmailAccount privateEmailAccount;

  @Override
  public Session get() {
    return JavaMailUtils.getSession(privateEmailAccount);
  }
}
