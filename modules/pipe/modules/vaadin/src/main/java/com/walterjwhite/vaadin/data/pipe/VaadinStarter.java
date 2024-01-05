// package com.walterjwhite.vaadin.data.pipe;
// import com.vaadin.server.VaadinServlet;
// import jakarta.servlet.*;
// import org.eclipse.jetty.server.Server;
// import org.eclipse.jetty.servlet.ServletContextHandler;
// import org.eclipse.jetty.servlet.ServletHolder;
/// ** Starts an instance of Jetty. */
// public class VaadinStarter {
//  public static void main(final String[] args) throws Exception {
//    final Server server = new Server(8080);
//    ServletContextHandler contextHandler =
//        new ServletContextHandler(ServletContextHandler.SESSIONS);
//    contextHandler.setContextPath(".");
//    server.setHandler(contextHandler);
//    final VaadinServlet vaadinServlet = new VaadinServlet();
//    //ui=>VaadinDataSinkWidget.class
//    contextHandler.addServlet(new ServletHolder(vaadinServlet), "/*");
//    //    final WebAppContext root = new WebAppContext();
//    //    root.setContextPath("/");
//    //    root.setDescriptor(
//    //        Thread.currentThread().getContextClassLoader().getResource("web.xml").getPath());
//    //    root.setResourceBase(".");
//    server.start();
//    server.join();
//  }
//  //    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
//  //    @VaadinServletConfiguration(ui = VaadinDataSinkWidget.class, productionMode = false)
//  //    public static class MyUIServlet extends VaadinServlet {}
// }
