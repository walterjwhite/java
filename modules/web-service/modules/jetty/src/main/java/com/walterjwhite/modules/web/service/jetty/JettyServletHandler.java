package com.walterjwhite.modules.web.service.jetty;

import com.google.inject.servlet.GuiceFilter;
import com.walterjwhite.modules.web.service.core.handler.AbstractServletHandler;
import com.walterjwhite.modules.web.service.core.property.WebServerPort;
import com.walterjwhite.property.impl.annotation.Property;
import jakarta.inject.Inject;
import jakarta.servlet.DispatcherType;
import java.util.EnumSet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;


public class JettyServletHandler extends AbstractServletHandler {
  protected Server server;
  protected ServletContextHandler servletContextHandler;

  @Inject
  public JettyServletHandler(@Property(WebServerPort.class) int port  ) {
    super(port  );
  }

  @Override
  public void run() throws Exception {
    server = new Server(port);
    servletContextHandler = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);




    setupGuice();
    doConfigure();

    server.setStopAtShutdown(true);
    server.start();
    server.join();
  }

  protected void setupGuice() {
    servletContextHandler.addEventListener(guiceServletContextListener);
    servletContextHandler.addFilter(GuiceFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));

    servletContextHandler.getInitParams().put("com.sun.jersey.api.json.POJOMappingFeature", "true");
    servletContextHandler
        .getInitParams()
        .put("com.sun.jersey.config.property.packages", "com.walterjwhite");
  }

  protected void doConfigure() {




  }

  @Override
  public void shutdown() throws Exception {
    server.stop();
  }
}
