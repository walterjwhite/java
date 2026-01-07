package com.walterjwhite.spring.web.logging;

import java.security.SecureRandom;
import java.util.Base64;

public interface RequestIdUtil {
  int RequestIdLength = 8;

  SecureRandom random = new SecureRandom();
  Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();

  static String generate() {
    byte[] buffer = new byte[RequestIdLength];
    random.nextBytes(buffer);
    return encoder.encodeToString(buffer);
  }
}
