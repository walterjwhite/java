package com.walterjwhite.data.pipe.impl;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;

public class QueueIterator implements Iterator {
  protected final BlockingQueue queue;

  public QueueIterator(BlockingQueue queue) {
    this.queue = queue;
  }

  @Override
  public boolean hasNext() {
    return true;
  }

  @Override
  public Object next() {
    try {
      return queue.take();
    } catch (InterruptedException e) {
      throw new RuntimeException("Error retrieving next element", e);
    }
  }
}
