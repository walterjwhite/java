package com.walterjwhite.email.javamail;

import com.walterjwhite.email.api.model.PrivateEmailAccount;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import javax.mail.Session;

// @RequiredArgsConstructor(onConstructor_ = @Inject)
public class SessionProvider implements Provider<Session> {
  protected final PrivateEmailAccount privateEmailAccount;

  @Inject
  public SessionProvider(PrivateEmailAccount privateEmailAccount) {
    this.privateEmailAccount = privateEmailAccount;
  }

  @Override
  public Session get() {
    return JavaMailUtils.getSession(privateEmailAccount);
  }
}
