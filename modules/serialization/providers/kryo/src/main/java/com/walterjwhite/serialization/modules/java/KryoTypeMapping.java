package com.walterjwhite.serialization.modules.java;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Registration;
import com.esotericsoftware.kryo.serializers.JavaSerializer;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum KryoTypeMapping {
  LocalDateTime(java.time.LocalDateTime.class) {
    public Registration register(Kryo kryo, KryoConfiguration kryoConfiguration) {
      return kryo.register(LocalDateTime.class, new JavaSerializer());
    }
  },
  Entity(java.time.LocalDateTime.class) {
    public Registration register(Kryo kryo, KryoConfiguration kryoConfiguration) {
      for (Class serializationClass : kryoConfiguration.getSerializationClasses()) {
        registerEntity(kryo, serializationClass);
      }

      return null;
    }

    protected Registration registerEntity(final Kryo kryo, final Class serializationClass) {
      if (!LocalDateTime.class.equals(serializationClass)) {
        return kryo.register(serializationClass);
      }

      return null;
    }
  };

  private final Class targetType;

  public abstract Registration register(Kryo kryo, KryoConfiguration kryoConfiguration);
}
