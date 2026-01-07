package com.walterjwhite.examples.vaadin;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.Synchronize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;

@PageTitle("Hello World with components")
@Route(value = "component", layout = MainLayout.class)
public class VaadinComponent extends Composite<Div> {
  public VaadinComponent() {
    PaperInput input = new PaperInput();

    Div greeting = new Div();

    updateGreeting(input, greeting);

    input.addValueChangeListener(event -> updateGreeting(input, greeting));

    input.setId("inputId");
    greeting.setId("componentsGreeting");

    /*
     * Add the input and the greeting to the Div component that makes up the
     * content of this Composite subclass
     */
    getContent().add(input, greeting);
  }

  private void updateGreeting(PaperInput input, Div greeting) {
    String name = input.getValue();

    if (name.isEmpty()) {
      greeting.setText("Please enter your name");
    } else {
      greeting.setText(String.format("Hello %s!", name));
    }
  }

  @Tag("paper-input")
  @HtmlImport("frontend://bower_components/paper-input/paper-input.html")
  public static class PaperInput extends Component {
    @Synchronize("value-changed")
    public String getValue() {
      return getElement().getProperty("value", "");
    }

    public Registration addValueChangeListener(ComponentEventListener<ValueChangeEvent> listener) {
      return addListener(ValueChangeEvent.class, listener);
    }
  }

  @DomEvent("value-changed")
  public static class ValueChangeEvent extends ComponentEvent<PaperInput> {
    public ValueChangeEvent(PaperInput source, boolean fromClient) {
      super(source, fromClient);
    }
  }
}
