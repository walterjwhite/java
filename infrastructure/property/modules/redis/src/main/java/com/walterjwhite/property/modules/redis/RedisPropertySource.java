package com.walterjwhite.property.modules.redis;

import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.property.api.annotation.PropertySourceIndex;
import com.walterjwhite.property.impl.source.AbstractSingularStringPropertySource;

@PropertySourceIndex(2)
public class RedisPropertySource extends AbstractSingularStringPropertySource {
  public RedisPropertySource(PropertyManager propertyManager, Class aClass) {
    super(propertyManager, aClass);
  }

  @Override
  protected String doGet(String propertyKey) {
    return null;
  }

  @Override
  protected void doSet(Class propertyType, Object value) {}

}
