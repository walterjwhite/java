package com.walterjwhite.data.pipe.modules.javafx.sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DataTable {
  protected final TableView tableView = new TableView();

  protected final ObservableList<Person> data =
      FXCollections.observableArrayList(
          new Person("Jacob", "Smith", "jacob.smith@example.com"),
          new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
          new Person("Ethan", "Williams", "ethan.williams@example.com"),
          new Person("Emma", "Jones", "emma.jones@example.com"),
          new Person("Michael", "Brown", "michael.brown@example.com"));

  public DataTable() {
    tableView.setEditable(true);

    TableColumn firstNameCol = new TableColumn("First Name");
    firstNameCol.setMinWidth(100);
    firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

    TableColumn lastNameCol = new TableColumn("Last Name");
    lastNameCol.setMinWidth(100);
    lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

    TableColumn emailCol = new TableColumn("Email");
    emailCol.setMinWidth(200);
    emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

    tableView.setItems(data);
    tableView.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
  }

  public TableView<Person> getTableView() {
    return tableView;
  }

  public ObservableList<Person> getData() {
    return data;
  }
}
