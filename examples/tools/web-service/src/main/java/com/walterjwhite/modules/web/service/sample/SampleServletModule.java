package com.walterjwhite.modules.web.service.sample;

import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.sun.jersey.spi.container.servlet.ServletContainer;
import com.walterjwhite.metrics.modules.prometheus.NetflixAtlasMetricsModule;
import com.walterjwhite.modules.web.service.core.annotation.ApplicationServletModule;
import io.swagger.v3.jaxrs2.integration.OpenApiServlet;
import jakarta.inject.Singleton;

/**
 * NOTE: this injector will be separate from the command-line injector. We could return the same
 * one, but it probably makes more sense to keep them separate.
 */
@ApplicationServletModule
public class SampleServletModule extends JerseyServletModule {

  protected void configureServlets() {
    install(new NetflixAtlasMetricsModule());

    bind(SampleService.class);
    bind(SampleWebService.class);
    bind(SampleWebService2.class);
    bind(ServletContainer.class).in(Singleton.class);
    bind(OpenApiServlet.class).in(Singleton.class);

    serve("/test/*").with(GuiceContainer.class);
    serve("/api/*").with(OpenApiServlet.class);
  }
}
