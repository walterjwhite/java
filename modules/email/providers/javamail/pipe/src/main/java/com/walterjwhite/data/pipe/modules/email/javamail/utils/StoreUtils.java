package com.walterjwhite.data.pipe.modules.email.javamail.utils;

import com.walterjwhite.email.api.model.PrivateEmailAccount;
import com.walterjwhite.email.modules.javamail.JavaMailUtils;
import jakarta.mail.MessagingException;
import jakarta.mail.Store;

public class StoreUtils {
  private StoreUtils() {}

  public static Store getStore(PrivateEmailAccount privateEmailAccount) {
    try {
      return JavaMailUtils.getStore(JavaMailUtils.getSession(privateEmailAccount));
    } catch (MessagingException e) {
      throw new RuntimeException("Error setting up store", e);
    }
  }
}
