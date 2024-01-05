package com.walterjwhite.examples.vaadin;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/** The navigation target to show when opening the application to root. */
@PageTitle("Hello worlds")
@Route(value = "", layout = MainLayout.class)
public class EntryPoint extends Div {

  /** Navigation target constructor. */
  public EntryPoint() {
    setText("Select version from the list above.");
  }
}
