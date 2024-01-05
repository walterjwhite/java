package com.walterjwhite.data.pipe.modules.javafx;

import com.walterjwhite.pipe.javafx.JavaFXDataSink;
import com.walterjwhite.pipe.javafx.JavaFXDataSinkConfiguration;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Tail a file and render the output to vaadin grid avoid buffering to minimize memory usage
 * configure amount of lines retained to cap memory usage
 */
public class JavaFXSampleApplication extends Application {
  public static JavaFXDataSink DATA_SINK;

  @Override
  public void start(Stage stage) throws Exception {
    Scene scene = new Scene(new Group());
    stage.setTitle("Table View Sample");
    stage.setWidth(300);
    stage.setHeight(500);

    final Label label = new Label("Label goes here");
    label.setFont(new Font("Arial", 20));

    final VBox vbox = new VBox();
    vbox.setSpacing(5);
    vbox.setPadding(new Insets(10, 0, 0, 10));
    vbox.getChildren().addAll(label, DATA_SINK.getTableView());

    ((Group) scene.getRoot()).getChildren().addAll(vbox);

    stage.setScene(scene);
    stage.show();
  }

  public static void main(final String[] arguments) throws Exception {
    // execute a command such as tailing a file, indefinite runtime
    // tail -f /var/log/messages

    JavaFXDataSinkConfiguration sinkConfiguration =
        new JavaFXDataSinkConfiguration(
            5,
            null,
            "",
            "JavaFX Data Sink",
            "JavaFX Data Sink example grid",
            "Name",
            "Description");

    JavaFXDataSink javaFXDataSink = new JavaFXDataSink();
    javaFXDataSink.configure(sinkConfiguration);

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

    DATA_SINK = javaFXDataSink;
    launch();
  }
}
