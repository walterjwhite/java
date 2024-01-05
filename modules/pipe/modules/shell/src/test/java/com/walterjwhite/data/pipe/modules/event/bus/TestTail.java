package com.walterjwhite.data.pipe.modules.event.bus;

import com.google.inject.Injector;
import com.walterjwhite.data.pipe.api.DataPipeConfiguration;
import com.walterjwhite.data.pipe.modules.event.bus.api.model.ShellSourceConfiguration;
import com.walterjwhite.data.pipe.modules.event.bus.api.model.SystemSinkConfiguration;
import com.walterjwhite.google.guice.GuiceHelper;
import com.walterjwhite.shell.api.model.ShellCommand;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tail a file and render the output to vaadin grid avoid buffering to minimize memory usage
 * configure amount of lines retained to cap memory usage
 */
public class TestTail {
  protected Injector injector;

  @Before
  public void onBefore() {
    GuiceHelper.addModules(new ShellTestModule(getClass()));

    GuiceHelper.setup();
    injector = GuiceHelper.getGuiceApplicationInjector();
  }

  @After
  public void onAfter() {}

  @Test
  public void test() {
    // execute a command such as tailing a file, indefinite runtime
    // tail -f /var/consumeOutput/messages
    ShellSourceConfiguration sourceConfiguration =
        new ShellSourceConfiguration(new ShellCommand().withCommandLine("tail -f /tmp/test"));

    SystemSinkConfiguration sinkConfiguration = new SystemSinkConfiguration(false);

    DataPipeConfiguration dataPipeConfiguration =
        new DataPipeConfiguration(sourceConfiguration, sinkConfiguration);

    //    Pipe pipe = injector.getInstance(Pipe.class);
    //    Pipe pipe =
    //        new DefaultPipe(
    //            dataPipeConfiguration, pipeSessionGroupConfiguration, pipeSessionConfiguration);
    //    pipe.pipe(dataPipeConfiguration);
  }
}
