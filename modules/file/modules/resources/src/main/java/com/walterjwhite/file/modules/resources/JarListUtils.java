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
    final List<String> files = new ArrayList<>();

    try {
      for (Path path : getPathsFromResourceJAR(folder)) {
        files.add(getResourcePath(path));
      }
    } catch (NoSuchFileException e) {
      // do nothing, the file doesn't exist
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

  private static List<Path> getPathsFromResourceJAR(String folder)
      throws URISyntaxException, IOException {
    URI uri = URI.create("jar:file:" + getJarPath());
    try (FileSystem fs = FileSystems.newFileSystem(uri, Collections.emptyMap())) {
      return Files.walk(fs.getPath(folder))
          .filter(Files::isRegularFile)
          .collect(Collectors.toList());
    }
  }

  private static String getJarPath() throws URISyntaxException {
    return JarListUtils.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
  }
}
