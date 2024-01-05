package com.walterjwhite.data.pipe.modules.javafx.sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TableViewSample6 extends Application {
  private static final DataTable dataTable = new DataTable();

  public static void main(String[] args) {
    launch(args);
  }

  // must have a public constructor
  private TableViewSample6() {}

  @Override
  public void start(Stage stage) {
    Scene scene = new Scene(new Group());
    stage.setTitle("Table View Sample");
    stage.setWidth(450);
    stage.setHeight(500);

    final Label label = new Label("Address Book");
    label.setFont(new Font("Arial", 20));

    final VBox vbox = new VBox();
    vbox.setSpacing(5);
    vbox.setPadding(new Insets(10, 0, 0, 10));
    vbox.getChildren().addAll(label, dataTable.getTableView());

    ((Group) scene.getRoot()).getChildren().addAll(vbox);

    stage.setScene(scene);
    stage.show();
  }
}
