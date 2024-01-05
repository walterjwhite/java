package com.walterjwhite.web.jetty.jaxrs;

import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
import com.walterjwhite.infrastructure.inject.providers.guice.hk2.GuiceResourceConfig;
import com.walterjwhite.web.jetty.JettyHandler;
import lombok.extern.slf4j.Slf4j;
//import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

@Slf4j
public class JaxRSJettyHandler implements JettyHandler {
  /*
  public void setup(final HandlerCollection handlerCollection) {
    final String jaxRSContextPath =
        ApplicationHelper.getApplicationInstance().getPropertyManager().get(JaxRSContextPath.class);
    final String jaxRSPackages =
        ApplicationHelper.getApplicationInstance().getPropertyManager().get(JaxRSPackages.class);

    final ServletContextHandler restContext =
        new ServletContextHandler(
            handlerCollection, jaxRSContextPath, ServletContextHandler.NO_SESSIONS);

    ServletHolder jerseyServlet = restContext.addServlet(ServletContainer.class, "/*");
    jerseyServlet.setInitOrder(1);

    jerseyServlet.setInitParameter("jersey.config.server.provider.packages", jaxRSPackages);
    jerseyServlet.setInitParameter(
        / *Application.class.getName()* / "javax.ws.rs.Application",
        GuiceResourceConfig.class.getName());

    LOGGER.error("jaxrs context : {}", jaxRSContextPath);
    LOGGER.error("jaxrs packages: {}", jaxRSPackages);
  }
  */
}
