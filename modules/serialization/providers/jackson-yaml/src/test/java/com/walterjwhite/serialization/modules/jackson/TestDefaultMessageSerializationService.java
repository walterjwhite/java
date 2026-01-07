package com.walterjwhite.serialization.modules.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walterjwhite.serialization.api.service.SerializationService;
import com.walterjwhite.serialization.jackson.Jackson;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

@Slf4j
public class TestDefaultMessageSerializationService {
  protected SerializationService serializationService = new Jackson(new ObjectMapper());

  @Test
  public void testSerialization() throws Exception {
    final TestSerialization testSerialization = new TestSerialization("Fred", "Rogers", 65);

    final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    serializationService.serialize(testSerialization, baos);

    final File sourceFile = new File("/tmp/serialization-test");
    IOUtils.write(baos.toByteArray(), new FileOutputStream(sourceFile));

    final Object deserialized =
        serializationService.deserialize(new FileInputStream(new File("/tmp/serialization-test")));
    LOGGER.info("deserialized:" + deserialized);


    sourceFile.delete();
  }
}
