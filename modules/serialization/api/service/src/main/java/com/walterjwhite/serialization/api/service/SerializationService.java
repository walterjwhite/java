package com.walterjwhite.serialization.api.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public interface SerializationService {

  void serialize(Serializable data, OutputStream outputStream) throws Exception;

  Serializable deserialize(InputStream inputStream) throws IOException;

  <EntityType> EntityType deserialize(
      InputStream inputStream, 
      Class<EntityType> entityType)
      throws IOException;


}
