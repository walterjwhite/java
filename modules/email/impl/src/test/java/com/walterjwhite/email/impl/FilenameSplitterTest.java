package com.walterjwhite.email.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FilenameSplitterTest {

  @Test
  public void testSplitter() {
    final String[] filenames = new String[] {"IMG_3333.jpg", ".", "IMG.2018.03.30.jpg"};

    for (final String filename : filenames) {
      final int index = filename.lastIndexOf(".");
      if (index > 0) {
        LOGGER.info(filename.substring(0, index));
        LOGGER.info(filename.substring(index + 1));
      } else {
        LOGGER.warn("unmatched");
      }
    }
  }
}
