package com.walterjwhite.pipe.javafx;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.*;

public class JavaFXApplication extends Application {
  private static AtomicBoolean started = new AtomicBoolean();
  public static List<JavaFXDataSink> DATA_SINKS = new ArrayList<>();
  private static final JFXPanel panel = new JFXPanel();

  private static AtomicBoolean initialized = new AtomicBoolean();

  public static void initialize() throws InterruptedException {
    if (!initialized.get()) {
      final CountDownLatch latch = new CountDownLatch(1);
      SwingUtilities.invokeLater(() -> latch.countDown());

      if (!latch.await(5L, TimeUnit.SECONDS)) throw new ExceptionInInitializerError();

      initialized.set(true);
    }
  }

  @Override
  public void start(Stage stage) throws Exception {
    //    Scene scene = panel.getScene();
    //    Scene scene = stage.getScene();
    Scene scene = new Scene(new Group());
    panel.setScene(scene);

    stage.setTitle("JavaFX View");
    //    stage.setWidth(300);
    //    stage.setHeight(500);

    //    final Label label = new Label("Label goes here");
    //    label.setFont(new Font("Arial", 20));

    final VBox vbox = new VBox();
    vbox.setSpacing(5);
    vbox.setPadding(new Insets(10, 0, 0, 10));

    for (JavaFXDataSink javaFXDataSink : DATA_SINKS)
      vbox.getChildren().addAll(javaFXDataSink.getTableView());

    ((Group) scene.getRoot()).getChildren().addAll(vbox);

    stage.setScene(scene);
    stage.show();

    scene.addEventHandler(
        KeyEvent.KEY_PRESSED,
        t -> {
          if (t.getCode() == KeyCode.Q && t.isControlDown()) {
            stage.close();
          }
        });
  }

  public static void main(final String[] arguments) {
    doStart(arguments);
  }

  private static synchronized void doStart(final String[] arguments) {
    if (!isStarted()) {
      // initialize guice
      launch(arguments);

      started.set(true);
    }
  }

  private static synchronized boolean isStarted() {
    return started.get();
  }
}
