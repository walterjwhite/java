/*
package com.walterjwhite.serialization.modules.java;

import java.util.Arrays;

public class HibernateJPAKryoProvider extends KryoProvider {
  public HibernateJPAKryoProvider(
      KryoConfiguration kryoConfiguration, EntityManager entityManager) {
    super(kryoConfiguration);
    register(kryoConfiguration, entityManager);
  }

  protected void register(KryoConfiguration kryoConfiguration, EntityManager entityManager) {
    Arrays.stream(JPAFieldType.values())
        .forEach(jpaFieldType -> jpaFieldType.register(kryo, kryoConfiguration, entityManager));
  }
}
*/
