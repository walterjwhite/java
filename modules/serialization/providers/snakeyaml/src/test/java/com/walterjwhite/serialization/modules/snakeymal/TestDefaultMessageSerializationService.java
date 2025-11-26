package com.walterjwhite.serialization.modules.snakeymal;

import com.walterjwhite.serialization.api.service.SerializationService;
import com.walterjwhite.serialization.modules.snakeyaml.Snakeyaml;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

public class TestDefaultMessageSerializationService {

  protected SerializationService serializationService = new Snakeyaml();

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
