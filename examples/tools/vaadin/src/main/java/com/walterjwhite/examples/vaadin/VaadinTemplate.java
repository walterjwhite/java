package com.walterjwhite.examples.vaadin;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;

@Tag("hello-world")
@HtmlImport("frontend://HelloWorld.html")
@PageTitle("Hello World with a template")
@Route(value = "template", layout = MainLayout.class)
class VaadinTemplate extends PolymerTemplate<VaadinTemplate.HelloWorldModel> {

  VaadinTemplate() {
    updateGreeting();

    getElement().addPropertyChangeListener("name", event -> updateGreeting());

    setId("template");
  }

  private void updateGreeting() {
    String name = getModel().getName();

    if (name == null || name.isEmpty()) {
      getModel().setGreeting("Please enter your name");
    } else {
      getModel().setGreeting(String.format("Hello %s!", name));
    }
  }

  public interface HelloWorldModel extends TemplateModel {
    String getName();

    void setGreeting(String greeting);
  }
}
