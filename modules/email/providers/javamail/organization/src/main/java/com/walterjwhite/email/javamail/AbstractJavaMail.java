package com.walterjwhite.email.javamail;

import com.walterjwhite.email.api.model.PrivateEmailAccount;
import jakarta.mail.MessagingException;
import jakarta.mail.Store;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Getter
public abstract class AbstractJavaMail implements AutoCloseable {
  protected transient PrivateEmailAccount privateEmailAccount;
  protected transient Store store;

  public void initialize(PrivateEmailAccount emailAccount) throws MessagingException {
    if (store != null) {
      return;
    }

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



  protected void onMessagingException(final MessagingException e) {
    LOGGER.warn("error", e);
  }

  @Override
  public void close() {
    closeStore();
  }
}
