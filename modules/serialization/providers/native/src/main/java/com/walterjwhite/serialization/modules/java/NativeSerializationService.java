package com.walterjwhite.serialization.modules.java;

import com.walterjwhite.serialization.api.service.SerializationService;
import jakarta.inject.Inject;
import java.io.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class NativeSerializationService implements SerializationService {
  @Override
  public void serialize(Serializable data, OutputStream outputStream) {
    try {
      try (ObjectOutputStream out = new ObjectOutputStream(outputStream)) {
        out.writeObject(data);
      }

    } catch (IOException e) {
      throw new RuntimeException("Error serializing", e);
    }
  }

  @Override
  public <EntityType> EntityType deserialize(
      InputStream inputStream, Class<EntityType> entityClass) {
    return (EntityType) deserialize(inputStream);
  }

  @Override
  public Serializable deserialize(InputStream inputStream) {
    try {
      try (ObjectInputStream in = new ObjectInputStream(inputStream)) {
        return ((Serializable) in.readObject());
      }

    } catch (IOException | ClassNotFoundException e) {
      throw new RuntimeException("Error serializing", e);
    }
  }
}
