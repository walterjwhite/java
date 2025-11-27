package com.walterjwhite.remote.impl.provider;


import com.walterjwhite.remote.api.model.Client;
import jakarta.inject.Inject;
import jakarta.inject.Provider;

public class QueueMonitorProvider implements Provider<Object /*QueueMonitor*/> {
  protected final Client client;


  @Inject
  public QueueMonitorProvider(Client client) {

    this.client = client;

  }


  @Override
  public Object get() {
    return null;
  }
}
