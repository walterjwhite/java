package com.walterjwhite.data.pipe.modules.event.bus;

import com.walterjwhite.data.pipe.impl.AbstractSink;
import com.walterjwhite.data.pipe.modules.event.bus.api.model.SystemSinkConfiguration;
import jakarta.inject.Inject;
import java.io.PrintStream;

public class SystemSink extends AbstractSink<String, SystemSinkConfiguration> {
  protected PrintStream printStream;

  @Inject
  public SystemSink() {}

  @Override
  protected void doConfigure() {
    if (!sinkConfiguration.isError()) printStream = System.out;
    else printStream = System.err;
  }

  @Override
  public void close() {
    printStream.flush();
    printStream.close();
  }

  @Override
  public void accept(String s) {
    printStream.println(s);
  }
}
