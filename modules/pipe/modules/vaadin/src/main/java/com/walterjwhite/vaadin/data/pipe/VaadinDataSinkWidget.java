/*
package com.walterjwhite.vaadin.data.pipe;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.ServiceException;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinServletService;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
@VaadinServletConfiguration(ui = VaadinDataSinkWidget.class, productionMode = false)
public class VaadinDataSinkWidget extends UI {
 protected final VaadinDataSink dataSink;
 protected final VaadinServlet vaadinServlet = new VaadinServlet();
 //ui=>VaadinDataSinkWidget.class
 public VaadinDataSinkWidget(VaadinDataSink dataSink) {
   this.dataSink = dataSink;
 }
 public void init() throws ServiceException {
   VaadinServletService vaadinServletService = new VaadinServletService(vaadinServlet, new
VaadinDeploymentConfiguration());
   vaadinServletService.init();
 }
 @Override
 protected void init(VaadinRequest request) {
   final VerticalLayout layout = new VerticalLayout();
   HorizontalLayout main = new HorizontalLayout(dataSink.getGrid() /*,form* /);
   main.setSizeFull();
   main.setExpandRatio(dataSink.getGrid(), 1);
   initializeScreen(layout);
   try {
     startServer();
   } catch (Exception e) {
     throw new RuntimeException("Error starting vaadin server", e));
   }
 }
 protected void initializeScreen(Layout layout) {
   //dataSink.update();
   setContent(layout);
   //        form.setVisible(false);
   //dataSink.configureEntitySelection();
 }
 //    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
 //  @VaadinServletConfiguration(ui = VaadinDataSinkWidget.class, productionMode = false)
 //    public static class MyUIServlet extends VaadinServlet {}
 protected void startServer() throws Exception {
   final Server server = new Server(8080);
   ServletContextHandler contextHandler =
       new ServletContextHandler(ServletContextHandler.SESSIONS);
   contextHandler.setContextPath(".");
   server.setHandler(contextHandler);
   contextHandler.addServlet(new ServletHolder(vaadinServlet), "/*");
   //    final WebAppContext root = new WebAppContext();
   //    root.setContextPath("/");
   //    root.setDescriptor(
   //        Thread.currentThread().getContextClassLoader().getResource("web.xml").getPath());
   //    root.setResourceBase(".");
   server.start();
   server.join();
 }
}
*/
