package com.walterjwhite.serialization.modules.jackson;

import com.walterjwhite.serialization.api.service.SerializationService;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class TestDefaultMessageSerializationService {
  protected SerializationService serializationService = new Jackson();

  @Test
  public void testSerialization() throws Exception {
    final TestSerialization testSerialization =
        new TestSerialization("Fred", "Rogers", 65, LocalDateTime.now());

    final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    serializationService.serialize(testSerialization, baos);

    final File sourceFile = new File("/tmp/serialization-test");
    IOUtils.write(baos.toByteArray(), new FileOutputStream(sourceFile));

    final Object deserialized =
        serializationService.deserialize(new FileInputStream(new File("/tmp/serialization-test")));
    System.out.println("deserialized:" + deserialized);
    System.out.println("deserialized:" + deserialized.getClass());
    LOGGER.info("deserialized:" + deserialized);

    //    System.out.println("deserialized:" +
    // serializationService.get().readValue(FileUtils.readFileToByteArray(new
    // File("/tmp/serialization-test")), TestSerialization.class));

    sourceFile.delete();
  }
}
