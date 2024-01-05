package com.walterjwhite.web.jetty.servlet;

import com.google.inject.servlet.GuiceFilter;
import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
import com.walterjwhite.web.jetty.JettyHandler;
import java.util.EnumSet;
import jakarta.servlet.DispatcherType;
import lombok.extern.slf4j.Slf4j;
//import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

@Slf4j
public class ServletJettyHandler implements JettyHandler {
  /*
  public void setup(final HandlerCollection handlerCollection) {
    final String servletContextPath =
        ApplicationHelper.getApplicationInstance()
            .getPropertyManager()
            .get(ServletContextPath.class);

    final ServletContextHandler servletContextHandler =
        new ServletContextHandler(
            handlerCollection, servletContextPath, ServletContextHandler.SESSIONS);

    // we're likely using guice as we could as well use jersey
    servletContextHandler.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));
    servletContextHandler.addServlet(new ServletHolder(new DefaultServlet()), "/*");

    LOGGER.error("servlet");
  }
  */
}
