package com.walterjwhite.remote.impl.plugins.heartbeat;

import com.google.inject.AbstractModule;

public class HeartbeatModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(HeartbeatMessageCallable.class);
    bind(ClientHeartbeatService.class);
  }
}
