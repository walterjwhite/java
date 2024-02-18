package com.walterjwhite.serialization.modules.java;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.walterjwhite.serialization.api.service.SerializationService;
import jakarta.inject.Inject;
import java.io.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class KryoSerializationService implements SerializationService {
  protected final Kryo kryo;

  @Override
  public void serialize(Serializable data, OutputStream outputStream) {
    try (final Output output = new Output(outputStream)) {
      kryo.writeClassAndObject(output, data);
    }
  }

  @Override
  public Serializable deserialize(InputStream inputStream, Class entityType) {
    try (final Input input = new Input(inputStream)) {


      return ((Serializable) kryo.readClassAndObject(input));
    }
  }

  @Override
  public Serializable deserialize(InputStream inputStream) {

    throw new UnsupportedOperationException("Need a class.");
  }
}
