package com.walterjwhite.metrics.modules.prometheus;

import com.sun.net.httpserver.HttpServer;
import com.walterjwhite.metrics.modules.prometheus.property.PrometheusServerContextPath;
import com.walterjwhite.metrics.modules.prometheus.property.PrometheusServerPort;
import com.walterjwhite.property.api.annotation.Property;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

@Singleton
public class PrometheusHttpServer {
  protected final HttpServer httpServer;

  @Inject
  public PrometheusHttpServer(
      @Property(PrometheusServerPort.class) int serverPort,
      @Property(PrometheusServerContextPath.class) String serverContextPath,
      final PrometheusMeterRegistry prometheusMeterRegistry)
      throws IOException {
    httpServer = HttpServer.create(new InetSocketAddress(serverPort), 0);

    httpServer.createContext(
        serverContextPath,
        httpExchange -> {
          String response = prometheusMeterRegistry.scrape();

          httpExchange.sendResponseHeaders(200, response.length());
          OutputStream os = httpExchange.getResponseBody();
          os.write(response.getBytes());
          os.close();
        });

    new Thread(httpServer::start).start();
  }
}
