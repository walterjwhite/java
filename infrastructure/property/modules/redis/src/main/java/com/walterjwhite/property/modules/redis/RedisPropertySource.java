package com.walterjwhite.property.modules.redis;

import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.property.api.annotation.PropertySourceIndex;
import com.walterjwhite.property.impl.source.AbstractSingularStringPropertySource;

/**
 * TODO: this is just a placeholder. Would it provide value to get one-time properties using redis?
 * If so, we need to configure a connection (host, port, password) to connect, where do those
 * settings come from? Rather, I think the utility in this is a dynamic value to store distributed
 * key-value mappings so the answer is the same across all nodes in a cluster.
 */
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

  //  protected transient RedisClient redisClient;
  //  protected transient RedisConnection<String, String> redisConnection;
  //
  //  protected void initialize(){
  //    this.redisClient = new RedisClient(
  //            RedisURI.create("redis://password@host:port"));
  //    redisConnection = redisClient.connect();
  //  }
  //
  //  public RedisPropertySource(PropertyManager propertyManager) {
  //    super(propertyManager, ConfigurableProperty.class);
  //  }
  //
  //  @Override
  //  protected String doGet(final String lookupValue) {
  //    return redisClient.(lookupValue);
  //  }
}
