package com.walterjwhite.web.jetty;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.HandlerContainer;

public interface JettyHandler {
  Handler setup(final HandlerContainer parent);
}
