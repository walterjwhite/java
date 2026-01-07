package com.walterjwhite.property.modules.walterjwhite.secrets.property;

import com.walterjwhite.logging.annotation.Sensitive;
import com.walterjwhite.property.api.SecretService;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.apache.commons.io.IOUtils;

public class WalterJWhiteSecretsPropertySource implements SecretService {

  @Override
  public void put(String key, String message, @Sensitive String plaintext) {
    try {
      final Process process = Runtime.getRuntime().exec(new String[] {"secrets", "put", key});

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
      final Process process =
          Runtime.getRuntime().exec(new String[] {"secrets", "get", "-o=stdout", key});

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
