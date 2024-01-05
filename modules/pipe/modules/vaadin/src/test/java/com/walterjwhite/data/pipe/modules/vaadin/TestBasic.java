package com.walterjwhite.data.pipe.modules.vaadin;

import com.walterjwhite.vaadin.data.pipe.VaadinDataSink;
import com.walterjwhite.vaadin.data.pipe.VaadinDataSinkConfiguration;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tail a file and render the output to vaadin grid avoid buffering to minimize memory usage
 * configure amount of lines retained to cap memory usage
 */
public class TestBasic {
  @Before
  public void onBefore() {}

  @After
  public void onAfter() {}

  @Test
  public void test() {
    // execute a command such as tailing a file, indefinite runtime
    // tail -f /var/log/messages

    VaadinDataSinkConfiguration sinkConfiguration =
        new VaadinDataSinkConfiguration(
            20,
            null,
            "",
            "Vaadin Data Sink",
            "Vaadin Data Sink example grid",
            "Name",
            "Description");

    VaadinDataSink vaadinDataSink = new VaadinDataSink();
    vaadinDataSink.configure(sinkConfiguration);

    final List<String[]> data = new ArrayList<>();
    data.add(new String[] {"BNW", "i3"});
    data.add(new String[] {"Mercedes", "CLK GTR"});

    vaadinDataSink.accept(data.get(0));
    vaadinDataSink.accept(data.get(1));
  }
}
