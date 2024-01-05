package com.walterjwhite.serialization.modules.java;

import com.esotericsoftware.kryo.Kryo;
import java.util.Arrays;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class KryoProvider implements Provider<Kryo> {
  protected final Kryo kryo = new Kryo();

  @Inject
  public KryoProvider(KryoConfiguration kryoConfiguration) {
    // Serializable ...
    Arrays.stream(KryoTypeMapping.values())
        .forEach(kryoTypeMapping -> kryoTypeMapping.register(kryo, kryoConfiguration));
  }

  //    kryo.setDefaultSerializer(AnotherGenericSerializer.class);
  //    kryo.addDefaultSerializer(List.class, CollectionSerializer.class);

  @Override
  public Kryo get() {
    return kryo;
  }
}
