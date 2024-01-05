package com.walterjwhite.data.pipe.modules.event.bus;

import com.google.common.eventbus.Subscribe;
import com.walterjwhite.data.pipe.impl.AbstractSource;
import com.walterjwhite.data.pipe.impl.QueueIterator;
import com.walterjwhite.data.pipe.modules.event.bus.api.model.EventBusSourceConfiguration;
import java.io.Serializable;
import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;

/** TODO: 1. get the appropriate source connection here 2. get the appropriate source query here */
public class EventBusSource extends AbstractSource<Serializable, EventBusSourceConfiguration> {
  protected final PriorityBlockingQueue queue = new PriorityBlockingQueue();

  protected final QueueIterator iterator = new QueueIterator(queue);

  @Override
  protected void doConfigure() {}

  public void close() {}

  @Subscribe
  public void onMessage(Serializable data) {
    queue.put(data);
  }

  @Override
  public Iterator<Serializable> iterator() {
    return iterator;
  }
}
