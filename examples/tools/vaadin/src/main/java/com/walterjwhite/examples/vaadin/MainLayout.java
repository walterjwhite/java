package com.walterjwhite.examples.vaadin;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import java.util.HashMap;
import java.util.Map;



@StyleSheet("frontend://styles.css")
public class MainLayout extends Composite<Div> implements RouterLayout, AfterNavigationObserver {

  private Map<String, RouterLink> targetPaths = new HashMap<>();

  private Div container;

  
  public MainLayout() {

    H1 heading = new H1("3 ways to say Hello");
    Text intro = new Text("Three different ways of implementing Hello World using Vaadin Flow");
    Div menu = buildMenu();


    container = new Div();
    container.addClassName("container");

    getContent().addClassName("main-view");
    getContent().add(heading, intro, menu, container);
  }

  private Div buildMenu() {

    RouterLink template = new RouterLink("Template", VaadinTemplate.class);
    template.setId("template-link");

    RouterLink components = new RouterLink("Component", VaadinComponent.class);
    components.setId("components-link");

    RouterLink elements = new RouterLink("Element", VaadinElement.class);
    elements.setId("elements-link");


    targetPaths.put(template.getHref(), template);
    targetPaths.put(components.getHref(), components);
    targetPaths.put(elements.getHref(), elements);

    HtmlContainer ul = new HtmlContainer("ul");
    ul.setClassName("topnav");
    ul.add(template, components, elements);

    Div menu = new Div();
    menu.setClassName("menu");

    menu.add(ul);
    return menu;
  }

  @Override
  public void showRouterLayoutContent(HasElement child) {

    container.removeAll();

    if (child != null) {
      container.add(new H2(child.getClass().getAnnotation(PageTitle.class).value()));
      container.add((Component) child);
    }
  }

  @Override
  public void afterNavigation(AfterNavigationEvent event) {
    targetPaths.values().forEach(link -> link.removeClassName("selected"));


    if (!event.getLocation().getPath().isEmpty()) {
      targetPaths.get(event.getLocation().getPath()).addClassName("selected");
    }
  }
}
