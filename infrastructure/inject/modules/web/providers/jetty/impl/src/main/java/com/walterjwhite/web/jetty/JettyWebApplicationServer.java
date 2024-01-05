package com.walterjwhite.web.jetty;

import com.walterjwhite.modules.web.service.core.WebApplicationServer;
import com.walterjwhite.modules.web.service.core.property.WebServerPort;
import com.walterjwhite.property.api.annotation.Property;
import java.util.Iterator;
import java.util.ServiceLoader;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
//import org.eclipse.jetty.server.handler.HandlerCollection;

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
    throw new UnsupportedOperationException("Not upgraded properly to Jetty 12, please implement handlers ...");
  }

  public void startup() throws Exception {
    server = new Server(port);
    setupHandlers(server);

    server.start();
  }

  protected void setupHandlers(final Server server) {
    //final HandlerCollection handlerCollection = new HandlerCollection();
    //server.setHandler(handlerCollection);

    ServiceLoader<JettyHandler> loader = ServiceLoader.load(JettyHandler.class);
    final Iterator<JettyHandler> jettyHandlerIterator = loader.iterator();
    while (jettyHandlerIterator.hasNext()) {
      final JettyHandler jettyHandler = jettyHandlerIterator.next();

      //jettyHandler.setup(handlerCollection);
    }
  }

  public void close() {
    server.destroy();
  }
}
