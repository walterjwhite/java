package com.walterjwhite.property.impl;

import com.walterjwhite.logging.annotation.Sensitive;
import com.walterjwhite.property.api.SecretService;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.apache.commons.io.IOUtils;

public class DefaultSecretService implements SecretService {
  @Sensitive
  @Override
  public void put(String key, String message, String plaintext) {
    try {
      final Process process =
          Runtime.getRuntime()
              .exec(new String[] {"secrets", "put", "-key", key, "-message", message});

      process.getOutputStream().write((plaintext + "\n").getBytes());
      process.getOutputStream().flush();
      process.waitFor();
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException("Error getting value:", e);
    }
  }

  @Sensitive
  @Override
  public String get(String key) {
    try {
      // dump secret to stdout
      final Process process =
          Runtime.getRuntime().exec(new String[] {"secrets", "get", "-o=s", key});

      try (final InputStream inputStream = process.getInputStream()) {

        final String output = IOUtils.toString(inputStream, Charset.defaultCharset());
        if (output == null || output.isEmpty()) {
          return null;
        }

        return output.trim();
      }
    } catch (IOException e) {
      throw new RuntimeException("Error getting value:", e);
    }
  }
}
