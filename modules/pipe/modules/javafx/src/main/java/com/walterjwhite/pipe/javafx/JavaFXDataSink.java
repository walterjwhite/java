package com.walterjwhite.pipe.javafx;

import com.walterjwhite.data.pipe.impl.AbstractSink;
import java.util.HashMap;
import java.util.Map;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Font;
import javafx.util.Callback;
import javafx.util.StringConverter;

/** Read-only scrolling view of data */
public class JavaFXDataSink extends AbstractSink<Object[], JavaFXDataSinkConfiguration> {
  protected TableView tableView;

  protected final ObservableList<Map<String, String>> uiData = FXCollections.observableArrayList();

  @Override
  protected void doConfigure() {
    try {
      JavaFXApplication.initialize();
    } catch (InterruptedException e) {
      throw new RuntimeException("Error initializing JavaFX environment", e);
    }

    final Label label = new Label(sinkConfiguration.getTitle());
    label.setFont(new Font("Arial", 20));

    tableView = new TableView();

    // table.setEditable(true);
    //    for(final String columnName:sinkConfiguration.getColumnNames()) {
    //        TableColumn tableColumn = new TableColumn(columnName);
    //      tableColumn.setMinWidth(200);
    //      tableColumn.setCellValueFactory(
    //              new PropertyValueFactory<>("email"));
    //
    //      table.getColumns().add(tableColumn);
    //    }

    //    tableView.setEditable(true);
    //    tableView.getSelectionModel().setCellSelectionEnabled(true);
    tableView.setFixedCellSize(25);
    tableView
        .prefHeightProperty()
        .bind(Bindings.size(tableView.getItems()).multiply(tableView.getFixedCellSize()).add(30));
    final DoubleProperty height = new SimpleDoubleProperty();
    height.setValue(sinkConfiguration.getMaxRows() * tableView.getFixedCellSize() + 30);

    // LOGGER.info("height:" + height);

    tableView.prefHeightProperty().bind(height);

    Callback<TableColumn<Map, String>, TableCell<Map, String>> cellFactoryForMap =
        (TableColumn<Map, String> p) ->
            new TextFieldTableCell(
                new StringConverter() {
                  @Override
                  public String toString(Object t) {
                    return t.toString();
                  }

                  @Override
                  public Object fromString(String string) {
                    return string;
                  }
                });

    for (final String columnName : sinkConfiguration.getColumnNames()) {
      TableColumn<Map, String> tableColumn = new TableColumn<>(columnName);
      tableColumn.setMinWidth(200);
      tableColumn.setCellValueFactory(new MapValueFactory(columnName));

      tableView.getColumns().add(tableColumn);
      tableColumn.setCellFactory(cellFactoryForMap);
    }

    tableView.setItems(uiData);
    JavaFXApplication.DATA_SINKS.add(this);
  }

  @Override
  public void close() throws Exception {}

  @Override
  public void accept(Object[] o) {
    final Map<String, String> row = new HashMap<>();
    int i = 0;
    for (String columnName : sinkConfiguration.getColumnNames()) {
      row.put(columnName, o[i++].toString());
    }

    while (uiData.size() >= sinkConfiguration.getMaxRows()) {
      uiData.remove(0);
    }

    uiData.add(row);
  }

  public TableView getTableView() {
    return tableView;
  }

  public void start() {
    JavaFXApplication.main(null);
  }
}
