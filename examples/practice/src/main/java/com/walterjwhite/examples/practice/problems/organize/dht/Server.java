package com.walterjwhite.examples.practice.problems.organize.dht;

import java.util.HashMap;
import java.util.Map;

public class Server {
  protected final transient Map<String, String> map = new HashMap<>();

  public String get(final String key) {
    return map.get(key);
  }

  public String put(final String key, final String value) {
    return map.put(key, value);
  }
}
