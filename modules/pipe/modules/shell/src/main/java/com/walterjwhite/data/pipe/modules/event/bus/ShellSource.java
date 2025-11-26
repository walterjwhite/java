package com.walterjwhite.data.pipe.modules.event.bus;

import com.walterjwhite.data.pipe.impl.AbstractSource;
import com.walterjwhite.data.pipe.impl.QueueIterator;
import com.walterjwhite.data.pipe.modules.event.bus.api.model.ShellSourceConfiguration;
import com.walterjwhite.shell.api.model.ShellCommand;
import com.walterjwhite.shell.api.service.ShellExecutionService;
import jakarta.inject.Inject;
import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;

public class ShellSource extends AbstractSource<String, ShellSourceConfiguration> {
  protected final PriorityBlockingQueue queue = new PriorityBlockingQueue();
  protected final QueueIterator iterator = new QueueIterator(queue);

  protected final ShellExecutionService shellExecutionService;
  protected ShellCommand shellCommand;
  protected final ShellOutputCollector shellOutputCollector;

  @Inject
  public ShellSource(ShellExecutionService shellExecutionService) {

    this.shellExecutionService = shellExecutionService;
    shellOutputCollector = new ShellOutputCollector(queue);
  }

  @Override
  protected void doConfigure() {
    try {
      shellExecutionService.run(shellCommand /*, shellOutputCollector*/);
    } catch (Exception e) {
      throw new RuntimeException("error running command", e);
    }
  }

  public void close() {
  }

  @Override
  public Iterator<String> iterator() {
    return iterator;
  }
}
