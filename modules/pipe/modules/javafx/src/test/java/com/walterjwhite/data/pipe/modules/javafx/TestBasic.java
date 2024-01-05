package com.walterjwhite.data.pipe.modules.javafx;

import com.walterjwhite.pipe.javafx.JavaFXApplication;
import com.walterjwhite.pipe.javafx.JavaFXDataSink;
import com.walterjwhite.pipe.javafx.JavaFXDataSinkConfiguration;
import javax.swing.*;
import org.junit.After;
import org.junit.Test;

/**
 * Tail a file and render the output to vaadin grid avoid buffering to minimize memory usage
 * configure amount of lines retained to cap memory usage
 */
public class TestBasic {
  @After
  public void onAfter() {}

  @Test
  public void test() throws Exception {
    // execute a command such as tailing a file, indefinite runtime
    // tail -f /var/log/messages

    doConfigure1();
    doConfigure2();
    doConfigure3();

    JavaFXApplication.main(null);
  }

  protected void doConfigure1() {
    JavaFXDataSink javaFXDataSink = configure(5, "1", "1", "Name", "Description");

    javaFXDataSink.accept(new String[] {"red", "the color red"});
    javaFXDataSink.accept(new String[] {"orange", "the color orange"});
    javaFXDataSink.accept(new String[] {"yellow", "the color yellow"});
    javaFXDataSink.accept(new String[] {"green", "the color green"});
    javaFXDataSink.accept(new String[] {"blue", "the color blue"});
    javaFXDataSink.accept(new String[] {"purple", "the color purple"});
    javaFXDataSink.accept(new String[] {"brown", "the color brown"});
    javaFXDataSink.accept(new String[] {"black", "the color black"});
    javaFXDataSink.accept(new String[] {"white", "the color white"});
    javaFXDataSink.accept(new String[] {"gray", "the color gray"});
    javaFXDataSink.accept(new String[] {"pink", "the color pink"});
  }

  protected void doConfigure2() {
    JavaFXDataSink javaFXDataSink = configure(10, "1", "1", "Number");

    javaFXDataSink.accept(new String[] {"1"});
    javaFXDataSink.accept(new String[] {"2"});
    javaFXDataSink.accept(new String[] {"3"});
    javaFXDataSink.accept(new String[] {"4"});
    javaFXDataSink.accept(new String[] {"5"});
    javaFXDataSink.accept(new String[] {"6"});
  }

  protected void doConfigure3() {
    JavaFXDataSink javaFXDataSink = configure(2, "1", "1", "Letter", "Description");

    javaFXDataSink.accept(new String[] {"a", "Letter A"});
    javaFXDataSink.accept(new String[] {"b", "Letter B"});
    javaFXDataSink.accept(new String[] {"c", "Letter C"});
    javaFXDataSink.accept(new String[] {"d", "Letter D"});
    javaFXDataSink.accept(new String[] {"e", "Letter E"});
  }

  protected JavaFXDataSink configure(
      final int maxRows,
      final String title,
      final String description,
      final String... columnNames) {
    JavaFXDataSinkConfiguration sinkConfiguration =
        new JavaFXDataSinkConfiguration(maxRows, null, "", title, description, columnNames);

    JavaFXDataSink javaFXDataSink = new JavaFXDataSink();
    javaFXDataSink.configure(sinkConfiguration);
    //    JavaFXApplication.DATA_SINKS.add(javaFXDataSink);
    //    javaFXDataSink.start();

    return javaFXDataSink;
  }
}
