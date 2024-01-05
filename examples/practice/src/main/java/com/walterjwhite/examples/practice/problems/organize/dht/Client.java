package com.walterjwhite.examples.practice.problems.organize.dht;

import java.util.HashMap;
import java.util.Map;

public class Client {
  // avoid having to program network ...
  protected final transient Map<String, Server> nodeMap = new HashMap<>();

  public String get(final String key) {
    return getServer(key).get(key);
  }

  public String put(final String key, final String value) {
    return getServer(key).put(key, value);
  }

  protected Server getServer(final String key) {
    return null;
  }

  // make this dht dynamic
  public void removeNode(final Server server) {}

  public void addNode(final Server server) {}
}
