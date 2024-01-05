package com.walterjwhite.examples.vaadin;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;

/**
 * Hello World view based on templates. The composition of elements is done based on the contents of
 * the HelloWorld.html file. Data is communicated between the client and the server based on the
 * HelloWorldModel.
 */
@Tag("hello-world")
// @HtmlImport("frontend://src/HelloWorld.html")
@HtmlImport("frontend://HelloWorld.html")
@PageTitle("Hello World with a template")
@Route(value = "template", layout = MainLayout.class)
class VaadinTemplate extends PolymerTemplate<VaadinTemplate.HelloWorldModel> {

  /** Template constructor. */
  VaadinTemplate() {
    // Set the initial greeting value
    updateGreeting();

    // Listen for value change events. The name property is updated by logic
    // in the template.
    getElement().addPropertyChangeListener("name", event -> updateGreeting());

    // Set up a DOM id value used for integration tests
    setId("template");
  }

  private void updateGreeting() {
    String name = getModel().getName();

    // Update the model based on the name
    if (name == null || name.isEmpty()) {
      getModel().setGreeting("Please enter your name");
    } else {
      getModel().setGreeting(String.format("Hello %s!", name));
    }
  }

  /**
   * A model public interface that defined the data that is communicated between the server and the
   * client.
   */
  public interface HelloWorldModel extends TemplateModel {
    /**
     * The name shown in the input is updated from the client.
     *
     * @return current name in model
     */
    String getName();

    /**
     * The greeting is updated from Java code on the server.
     *
     * @param greeting greeting to set to the model
     */
    void setGreeting(String greeting);
  }
}
