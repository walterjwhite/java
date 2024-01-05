package com.walterjwhite.serialization.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walterjwhite.serialization.api.service.JSON;
import com.walterjwhite.serialization.api.service.SerializationService;
import com.walterjwhite.serialization.api.service.YAML;
import java.io.*;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class Jackson implements SerializationService, JSON, YAML {
  protected final ObjectMapper objectMapper;

  @Override
  public void serialize(Serializable data, OutputStream outputStream) throws Exception {
    outputStream.write(objectMapper.writeValueAsBytes(data));
  }

  @Override
  public Serializable deserialize(InputStream inputStream) throws IOException {
    return ((Serializable) objectMapper.readValue(inputStream, Object.class));
  }

  @Override
  public <EntityType extends Serializable> EntityType deserialize(
      InputStream inputStream, Class<EntityType> entityType) throws IOException {
    return objectMapper.readValue(inputStream, entityType);
  }
}
