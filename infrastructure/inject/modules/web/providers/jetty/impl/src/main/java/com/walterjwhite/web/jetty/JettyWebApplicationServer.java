package com.walterjwhite.web.jetty;

import com.walterjwhite.modules.web.service.core.WebApplicationServer;
import com.walterjwhite.modules.web.service.core.property.WebServerPort;
import com.walterjwhite.property.api.annotation.Property;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.Iterator;
import java.util.ServiceLoader;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;

@Getter
@Setter
@Slf4j
@Singleton
public class JettyWebApplicationServer implements WebApplicationServer {
  protected transient Server server;

  protected final int port;

  @Inject
  public JettyWebApplicationServer(@Property(WebServerPort.class) final int port) {
    this.port = port;
  }

  public void startup() throws Exception {
    server = new Server(port);
    setupHandlers();

    server.start();
  }

  protected void setupHandlers() {
    final ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();
    final ServiceLoader<JettyHandler> loader = ServiceLoader.load(JettyHandler.class);
    final Iterator<JettyHandler> jettyHandlerIterator = loader.iterator();
    while (jettyHandlerIterator.hasNext()) {
      final JettyHandler jettyHandler = jettyHandlerIterator.next();

      contextHandlerCollection.addHandler(jettyHandler.setup(null));

      LOGGER.info("handler: {}", jettyHandler);
    }

    server.setHandler(contextHandlerCollection);
  }

  public void close() {
    server.destroy();
  }
}
