package com.walterjwhite.serialization.modules.snakeymal;

import com.google.inject.Injector;
import com.walterjwhite.google.guice.GuiceHelper;
import com.walterjwhite.serialization.api.service.SerializationService;
import com.walterjwhite.serialization.modules.snakeyaml.SnakeyamlSerializationServiceModule;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

public class TestDefaultMessageSerializationService {
  private static Injector GUICE_INJECTOR;

  protected SerializationService serializationService;

  @Before
  public void before() {
    GuiceHelper.addModules(new SnakeyamlSerializationServiceModule());
    GuiceHelper.setup();

    serializationService =
        GuiceHelper.getGuiceApplicationInjector().getInstance(SerializationService.class);
  }

  @Test
  public void testSerialization() throws Exception {
    final TestSerialization testSerialization = new TestSerialization("Fred", "Rogers", 65);

    final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    serializationService.serialize(testSerialization, baos);

    final File sourceFile = new File("/tmp/serialization-test");
    IOUtils.write(baos.toByteArray(), new FileOutputStream(sourceFile));

    sourceFile.delete();
  }
}
