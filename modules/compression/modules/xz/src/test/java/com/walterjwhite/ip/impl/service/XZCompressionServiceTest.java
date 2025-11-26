package com.walterjwhite.ip.impl.service;

import com.walterjwhite.compression.modules.XZCompressionService;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.junit.jupiter.api.Test;

public class XZCompressionServiceTest {
  @Test
  public void testCompressingData() throws IOException {
    final String message = "Hello from " + getClass();

    XZCompressionService compressionService = new XZCompressionService();

    final File outputFile = new File("/tmp/compressed-test");
    compressionService.compress(
        new ByteArrayInputStream(message.getBytes()), new FileOutputStream(outputFile));

    final OutputStream output = new ByteArrayOutputStream();
    compressionService.decompress(new FileInputStream(outputFile), output);

    outputFile.delete();

    System.out.println("read: " + output);
  }
}
