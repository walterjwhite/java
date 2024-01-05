package com.walterjwhite.index.service;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import com.walterjwhite.serialization.api.service.SerializationService;
import java.io.ByteArrayOutputStream;

public enum IndexSerializationType implements ConfigurableProperty {
  @DefaultValue
  Default() {
    protected transient SerializationService serializationService;

    public byte[] serialize(final AbstractEntity entity) throws Exception {
      initialize();

      try (final ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
        serializationService.serialize(entity, baos);

        return baos.toByteArray();
      }
    }

    protected void initialize() {
      if (serializationService == null) {
        serializationService = null;
      }
    }
  };

  public abstract byte[] serialize(final AbstractEntity entity) throws Exception;
}
