/*
package com.walterjwhite.serialization.modules.java;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Registration;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.collection.internal.PersistentBag;
import org.hibernate.collection.internal.PersistentSet;
import org.hibernate.engine.spi.SharedSessionContractImplementor;

@Getter
@RequiredArgsConstructor
public enum JPAFieldType {
  Bag(PersistentBag.class) {
    public Registration register(
        Kryo kryo, KryoConfiguration kryoConfiguration, EntityManager entityManager) {
      return kryo.register(
          PersistentBag.class,
          new FieldSerializer(kryo, PersistentBag.class) {
            public Object create(Kryo kryo, Input input, Class type) {
              return (new PersistentBag(
                  (SharedSessionContractImplementor) entityManager.unwrap(Session.class)));
            }
          });
    }
  },
  Set(PersistentSet.class) {
    public Registration register(
        Kryo kryo, KryoConfiguration kryoConfiguration, EntityManager entityManager) {
      return kryo.register(
          PersistentSet.class,
          new FieldSerializer(kryo, PersistentSet.class) {
            public Object create(Kryo kryo, Input input, Class type) {
              return (new PersistentSet(
                  (SharedSessionContractImplementor) entityManager.unwrap(Session.class)));
            }
          });
    }
  };
  private final Class fieldType;

  public abstract Registration register(
      Kryo kryo, KryoConfiguration kryoConfiguration, EntityManager entityManager);
}
*/
