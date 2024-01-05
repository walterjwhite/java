package com.walterjwhite.remote.impl.provider;

// import com.walterjwhite.queuedJob.api.enumeration.QueueType;
// import com.walterjwhite.queuedJob.api.model.Queue;
// import com.walterjwhite.queuedJob.api.model.QueueMonitor;

import com.walterjwhite.remote.api.model.Client;
import jakarta.inject.Inject;
import jakarta.inject.Provider;

public class QueueMonitorProvider implements Provider<Object /*QueueMonitor*/> {
  protected final Client client;
  //  protected final QueueMonitor queueMonitor;

  @Inject
  public QueueMonitorProvider(Client client) {

    this.client = client;

    //    Queue allQueue = new Queue("all", QueueType.All);
    //    Queue selfQueue = new Queue(Integer.toString(client.getId()), QueueType.Self);
    //
    //    queueMonitor = new QueueMonitor(Integer.toString(client.getId()));
    //    queueMonitor.getQueues().add(allQueue);
    //    queueMonitor.getQueues().add(selfQueue);
  }

  //  @Override
  //  public QueueMonitor get() {
  //    return queueMonitor;
  //  }

  @Override
  public Object get() {
    return null;
  }
}
