package com.walterjwhite.email.modules.javamail;

import com.walterjwhite.email.api.model.PrivateEmailAccount;
import com.walterjwhite.logging.annotation.LogStackTrace;
import com.walterjwhite.logging.enumeration.LogLevel;
import javax.mail.MessagingException;
import javax.mail.Store;
import lombok.Getter;

// @RequiredArgsConstructor
@Getter
public abstract class AbstractJavaMail implements AutoCloseable {
  protected transient PrivateEmailAccount privateEmailAccount;
  protected transient Store store;

  public void initialize(PrivateEmailAccount emailAccount) throws MessagingException {
    if (store != null) return;

    this.privateEmailAccount = emailAccount;
    store = JavaMailUtils.getStore(JavaMailUtils.getSession(privateEmailAccount));
  }

  protected void closeStore() {
    if (store != null) {

      try {
        store.close();
      } catch (MessagingException e) {
        onMessagingException(e);
      }
    }
  }

  @LogStackTrace(level = LogLevel.WARN)
  protected void onMessagingException(final MessagingException e) {}

  @Override
  public void close() {
    closeStore();
  }
}
