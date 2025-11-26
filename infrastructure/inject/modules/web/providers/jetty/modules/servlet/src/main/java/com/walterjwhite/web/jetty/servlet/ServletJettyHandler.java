package com.walterjwhite.web.jetty.servlet;

import com.google.inject.servlet.GuiceFilter;
import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
import com.walterjwhite.web.jetty.JettyHandler;
import jakarta.servlet.DispatcherType;
import java.util.EnumSet;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.HandlerContainer;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

@Slf4j
public class ServletJettyHandler implements JettyHandler {
  public Handler setup(final HandlerContainer parent) {
    final String servletContextPath =
        ApplicationHelper.getApplicationInstance()
            .getPropertyManager()
            .get(ServletContextPath.class);

    final ServletContextHandler servletContextHandler =
        new ServletContextHandler(parent, servletContextPath, ServletContextHandler.SESSIONS);

    servletContextHandler.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));
    servletContextHandler.addServlet(new ServletHolder(new DefaultServlet()), "/*");

    LOGGER.error("servlet");
    return servletContextHandler;
  }
}
