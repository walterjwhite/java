package com.walterjwhite.data.pipe.modules.javafx;

import com.walterjwhite.file.modules.tailer.Tailer;
import com.walterjwhite.pipe.javafx.JavaFXDataSink;
import com.walterjwhite.pipe.javafx.JavaFXDataSinkConfiguration;
import java.io.File;
import java.util.Iterator;

/**
 * Tail a file and render the output to vaadin grid avoid buffering to minimize memory usage
 * configure amount of lines retained to cap memory usage
 */
public class JavaFXSampleApplicationTailFile {
  public static void main(final String[] arguments) throws Exception {
    // execute a command such as tailing a file, indefinite runtime
    // tail -f /var/log/messages

    JavaFXDataSinkConfiguration sinkConfiguration =
        new JavaFXDataSinkConfiguration(
            5, null, "", "JavaFX Data Sink", "JavaFX Data Sink example grid", "Tail (line)");

    JavaFXDataSink javaFXDataSink = new JavaFXDataSink();
    javaFXDataSink.configure(sinkConfiguration);

    final TailSource tailSource = new TailSource();

    //    final Pipe pipe = new DefaultPipe(dataPipeConfiguration, null, null);
    //    pipe.pipe(dataPipeConfiguration);
    //    pipe.start();

    javaFXDataSink.accept(new String[] {"red"});
    javaFXDataSink.accept(new String[] {"orange"});

    //    javaFXDataSink.start();
    System.out.println("starting runnable");
    final Thread tempRunnable = new Thread(new TempRunnable(tailSource, javaFXDataSink));
    tempRunnable.start();

    Tailer tailer = new Tailer(new File("/tmp/test"), tailSource);
    System.out.println("started tailer");

    final Thread temp2Runnable = new Thread(new TempMainRunnable(javaFXDataSink));
    temp2Runnable.start();
    System.out.println("started temp2");
    // tailer.run();

    System.out.println("running tailer");
    temp2Runnable.join();
    tempRunnable.join();
  }

  private static class TempMainRunnable implements Runnable {
    protected final JavaFXDataSink javaFXDataSink;

    private TempMainRunnable(JavaFXDataSink javaFXDataSink) {
      this.javaFXDataSink = javaFXDataSink;
    }

    @Override
    public void run() {
      javaFXDataSink.start();
      System.out.println("started javafx datasink");
    }
  }

  private static class TempRunnable implements Runnable {
    protected final TailSource tailSource;
    protected final JavaFXDataSink javaFXDataSink;

    private TempRunnable(TailSource tailSource, JavaFXDataSink javaFXDataSink) {
      this.tailSource = tailSource;
      this.javaFXDataSink = javaFXDataSink;
    }

    @Override
    public void run() {
      //      javaFXDataSink.accept(new String[] {"test"});

      try {
        final Iterator<String> tailSourceIterator = tailSource.iterator();

        while (tailSourceIterator.hasNext()) {
          final String next = tailSourceIterator.next();
          System.out.println("TempRunnable.run.next:" + next);
          javaFXDataSink.accept(new String[] {next});
        }
      } catch (Exception e) {
        handleError(e);
      }
    }
  }

  protected void handleError(Exception e) {}
}
