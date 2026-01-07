package com.walterjwhite.examples.serialization;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.serialization.api.service.SerializationService;
import jakarta.inject.Inject;
import java.io.*;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class SerializationExampleCommandLineHandler implements CommandLineHandler {
  protected final SerializationService serializationService;

  @Inject
  public SerializationExampleCommandLineHandler(
      SerializationService serializationService) {
    this.serializationService = serializationService;
  }

  protected void setupKyro() {

  }

  @Override
  public void run(String... arguments) throws Exception {
    final Duration input = Duration.ofHours(1);

    final Duration output = file(input);

    final Car car =
        new Car(
            "Ford",
            "GT90",
            "1999",
            "white",
            new Engine("Ford Racing", 7200, 6500, 2000),
            Duration.ofMillis(150),
            Map.of("switch1", "on", "switch2", "off", "dial1", "0.37"),
            AxleType.Solid,
            List.of(
                new SwitchSetting(Switch.Analog, new String[] {"tachometer", "fuel"}),
                new SwitchSetting(Switch.Digital, new String[] {"door.sensor", "running"})));
  }

  protected <Type extends Serializable> Type file(final Type input) throws Exception {
    final File tempFile = File.createTempFile("serialization", "yaml");

    try {
      serializationService.serialize(input, new FileOutputStream(tempFile));
      return (Type)
          serializationService.deserialize(new FileInputStream(tempFile), input.getClass());
    } finally {
    }
  }

  protected <Type extends Serializable> Type byteArray(final Type input) throws Exception {
    final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    serializationService.serialize(input, baos);
    baos.flush();
    final byte[] data = baos.toByteArray();

    return (Type)
        serializationService.deserialize(new ByteArrayInputStream(data), input.getClass());
  }
}
