package com.walterjwhite.data.pipe.modules.cli;

import com.google.inject.Injector;
import com.walterjwhite.csv.modules.apache.ApacheCommonsCSVModule;
import com.walterjwhite.data.pipe.api.DataPipeConfiguration;
import com.walterjwhite.data.pipe.impl.DataPipeModule;
import com.walterjwhite.data.pipe.modules.csv.CSVDataPipeModule;
import com.walterjwhite.data.pipe.modules.csv.model.CSVConfiguration;
import com.walterjwhite.data.pipe.modules.csv.model.CSVSinkConfiguration;
import com.walterjwhite.data.pipe.modules.csv.model.CSVSourceConfiguration;
import com.walterjwhite.google.guice.GuiceHelper;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCopy {
  protected Injector injector;

  @Before
  public void onBefore() {
    GuiceHelper.addModules(
        new DataPipeModule(), new CSVDataPipeModule(), new ApacheCommonsCSVModule());
    GuiceHelper.setup();
    injector = GuiceHelper.getGuiceApplicationInjector();
  }

  @After
  public void onAfter() {}

  @Test
  public void doTest() throws Exception {
    try (final FileOutputStream f = new FileOutputStream("/tmp/source.csv")) {
      f.write("a,b,c\n".getBytes(Charset.defaultCharset()));
      f.write("1,10,100\n".getBytes(Charset.defaultCharset()));
      f.write("1,20,200\n".getBytes(Charset.defaultCharset()));

      f.flush();
    }

    CSVSourceConfiguration csvSourceConfiguration =
        new CSVSourceConfiguration(
            new CSVConfiguration("/tmp/source.csv", new String[] {"a", "b", "c"}));

    CSVSinkConfiguration csvSinkConfiguration =
        new CSVSinkConfiguration(
            new CSVConfiguration("/tmp/sink.csv", new String[] {"a", "b", "c"}));

    DataPipeConfiguration dataPipeConfiguration =
        new DataPipeConfiguration(csvSourceConfiguration, csvSinkConfiguration);

    //    Pipe pipe =
    //        new DefaultPipe(
    //            dataPipeConfiguration, pipeSessionGroupConfiguration, pipeSessionConfiguration);
    //    pipe.pipe(dataPipeConfiguration);
  }
}
