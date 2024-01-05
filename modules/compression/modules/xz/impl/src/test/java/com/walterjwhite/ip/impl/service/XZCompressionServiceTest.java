package com.walterjwhite.ip.impl.service;

import com.google.inject.Injector;
import com.walterjwhite.compression.modules.CompressionModule;
import com.walterjwhite.encryption.api.service.CompressionService;
import com.walterjwhite.google.guice.GuiceHelper;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

public class XZCompressionServiceTest {
  private static Injector GUICE_INJECTOR;

  @Before
  public void before() {
    GuiceHelper.addModules(new CompressionModule());
    GuiceHelper.setup();
    GUICE_INJECTOR = GuiceHelper.getGuiceApplicationInjector();
  }

  @Test
  public void testCompressingData() throws IOException {
    final byte[] rawData = new byte[1024];
    for (int i = 0; i < rawData.length; i++) {
      rawData[i] = 0;
    }

    CompressionService compressionService = GUICE_INJECTOR.getInstance(CompressionService.class);

    final File outputFile = new File("/tmp/compressed-test");
    IOUtils.copy(
        new ByteArrayInputStream(rawData),
        compressionService.getCompressionStream(new FileOutputStream(outputFile)));

    outputFile.delete();
  }
}
