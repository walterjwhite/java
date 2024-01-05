package com.walterjwhite.data.pipe.modules.javafx;

import com.walterjwhite.data.pipe.api.source.Source;
import com.walterjwhite.data.pipe.impl.QueueIterator;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;

public class TailSource extends OutputStream implements Source<String, TailConfiguration> {
  // protected final SynchronousQueue<String> queue = new SynchronousQueue<>();
  protected final PriorityBlockingQueue<String> queue = new PriorityBlockingQueue<>();

  protected StringBuilder buffer = new StringBuilder();

  @Override
  public void write(int i) throws IOException {
    if (i == '\n') {
      queue.put(buffer.toString());
      System.out.println("put:" + buffer.toString());
      buffer = new StringBuilder();
    } else {
      buffer.append((char) i);
    }
  }

  @Override
  public void configure(TailConfiguration sourceConfiguration) {}

  // blocks until there is more data
  @Override
  public Iterator<String> iterator() {
    return new QueueIterator(queue);
  }
}
