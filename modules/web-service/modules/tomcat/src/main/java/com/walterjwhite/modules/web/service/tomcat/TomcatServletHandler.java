package com.walterjwhite.modules.web.service.guice;

import com.walterjwhite.google.guice.cli.service.AbstractCommandLineHandler;
import java.io.File;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class TomcatServletHandler extends AbstractCommandLineHandler {
  protected Tomcat tomcat;

  public TomcatServletHandler(int shutdownTimeoutInSeconds) {
    super(shutdownTimeoutInSeconds);
  }

  @Override
  public void run(String... arguments) throws Exception {
    tomcat = new Tomcat();

    tomcat.setPort(getPort());

    final String webAppLocation = getWebAppLocation();
    StandardContext ctx =
        (StandardContext) tomcat.addWebapp("/", new File(webAppLocation).getAbsolutePath());

    File additionWebInfClasses = new File("target/classes");
    WebResourceRoot resources = new StandardRoot(ctx);
    resources.addPreResources(
        new DirResourceSet(
            resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
    ctx.setResources(resources);

    tomcat.start();
    tomcat.getServer().await();
  }

  protected int getPort() {
    String webPort = System.getenv("PORT");
    if (webPort == null || webPort.isEmpty()) {
      return 8080;
    }

    return Integer.valueOf(webPort);
  }

  protected String getWebAppLocation() {
    return "src/main/webapp/";
  }

  @Override
  protected void onShutdown() throws Exception {
    tomcat.stop();

    super.onShutdown();
  }
}
