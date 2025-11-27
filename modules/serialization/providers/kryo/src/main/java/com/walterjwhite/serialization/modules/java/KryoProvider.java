package com.walterjwhite.serialization.modules.java;

import com.esotericsoftware.kryo.Kryo;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class KryoProvider implements Provider<Kryo> {
  protected final Kryo kryo = new Kryo();

  @Inject
  public KryoProvider(KryoConfiguration kryoConfiguration) {
    Arrays.stream(KryoTypeMapping.values())
        .forEach(kryoTypeMapping -> kryoTypeMapping.register(kryo, kryoConfiguration));
  }


  @Override
  public Kryo get() {
    return kryo;
  }
}
