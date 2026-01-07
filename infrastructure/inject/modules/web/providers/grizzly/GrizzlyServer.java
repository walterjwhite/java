package com.walterjwhite.infrastructure.inject.providers.guice;

import com.walterjwhite.infrastructure.inject.core.service.StartupAware;
import java.net.URI;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

public class GrizzlyServer implements StartupAware {
  private static final URI BASE_URI = URI.create("http://localhost:8080/base/");
  public static final String ROOT_PATH = "helloworld";
  protected final HttpServer server =
      GrizzlyHttpServerFactory.createHttpServer(BASE_URI, null, false);

  @Override
  public void startup() throws Exception {
    setupServletContainer();
    server.start();
  }

  protected void setupServletContainer() {
  }

  @Override
  public void close() throws Exception {
    server.shutdownNow();
  }
}
