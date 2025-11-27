package com.walterjwhite.file.modules.resources;

import java.io.InputStream;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JarReadUtils {
  public static InputStream getFileFromResourceAsStream(String fileName) {
    final ClassLoader classLoader = JarListUtils.class.getClassLoader();
    final InputStream inputStream = classLoader.getResourceAsStream(fileName);

    if (inputStream == null) {
      throw new IllegalArgumentException("file not found! " + fileName);
    }
    return inputStream;
  }
}
