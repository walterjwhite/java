package com.walterjwhite.file.modules.resources;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JarListUtils {
  public static List<String> getFiles(final String folder) throws IOException, URISyntaxException {
    return getFiles(JarListUtils.class, folder);
  }

  public static List<String> getFiles(final Class targetClass, final String folder)
      throws IOException, URISyntaxException {
    final List<String> files = new ArrayList<>();

    try {
      for (Path path : getPathsFromResourceJAR(targetClass, folder)) {
        files.add(getResourcePath(path));
      }
    } catch (NoSuchFileException e) {
    }
    return files;
  }

  private static String getResourcePath(final Path inputPath) {
    String outputPath = inputPath.toString();
    if (outputPath.startsWith("/")) {
      return outputPath.substring(1);
    }

    return outputPath;
  }

  private static List<Path> getPathsFromResourceJAR(final Class targetClass, String folder)
      throws URISyntaxException, IOException {
    URI uri = URI.create("jar:file:" + getJarPath(targetClass));
    try (FileSystem fs = FileSystems.newFileSystem(uri, Collections.emptyMap())) {
      return Files.walk(fs.getPath(folder))
          .filter(Files::isRegularFile)
          .collect(Collectors.toList());
    }
  }

  private static String getJarPath(final Class targetClass) throws URISyntaxException {
    return targetClass.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
  }
}
