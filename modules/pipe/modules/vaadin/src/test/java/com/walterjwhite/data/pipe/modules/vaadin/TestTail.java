package com.walterjwhite.data.pipe.modules.vaadin;

import com.google.inject.Injector;
import com.walterjwhite.data.pipe.api.DataPipeConfiguration;
import com.walterjwhite.data.pipe.modules.csv.model.CSVConfiguration;
import com.walterjwhite.data.pipe.modules.csv.model.CSVSourceConfiguration;
import com.walterjwhite.google.guice.GuiceHelper;
import com.walterjwhite.vaadin.data.pipe.VaadinDataSinkConfiguration;
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
    GuiceHelper.addModules(new VaadinTestModule(getClass()));

    injector = GuiceHelper.getGuiceApplicationInjector();
  }

  @After
  public void onAfter() {}

  @Test
  public void test() {
    // execute a command such as tailing a file, indefinite runtime
    // tail -f /var/log/messages

    CSVSourceConfiguration sourceConfiguration =
        new CSVSourceConfiguration(new CSVConfiguration("/tmp/test.csv", "Name", "Description"));
    VaadinDataSinkConfiguration sinkConfiguration =
        new VaadinDataSinkConfiguration(
            20, null, "", "Vaadin Data Sink", "Vaadin Data Sink example grid");

    DataPipeConfiguration configuration =
        new DataPipeConfiguration(sourceConfiguration, sinkConfiguration);
    //    DefaultPipe pipe =
    //        new DefaultPipe(
    //            dataPipeConfiguration, pipeSessionGroupConfiguration, pipeSessionConfiguration);
    //    pipe.pipe(configuration);
  }
}
