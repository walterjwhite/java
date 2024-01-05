package com.walterjwhite.file.modules.resources;

import java.io.InputStream;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JarReadUtils {
  public static InputStream getFileFromResourceAsStream(String fileName) {
    ClassLoader classLoader = JarListUtils.class.getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream(fileName);

    // the stream holding the file content
    if (inputStream == null) {
      throw new IllegalArgumentException("file not found! " + fileName);
    }
    return inputStream;
  }
}
