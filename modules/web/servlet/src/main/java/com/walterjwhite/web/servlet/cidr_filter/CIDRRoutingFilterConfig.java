package com.walterjwhite.web.servlet.cidr_filter;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.util.EnumSet;

@WebListener
public class CIDRRoutingFilterConfig implements ServletContextListener {
  private static final String FILTER_NAME = "cidrRoutingFilter";
  private static final String URL_PATTERN = "/*";
  private static final String INIT_PARAM_RULES_LOCATION = "rules.location";

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    ServletContext ctx = sce.getServletContext();

    FilterRegistration.Dynamic reg = ctx.addFilter(FILTER_NAME, CIDRRoutingFilter.class.getName());

    if (reg == null) {
      ctx.log("CidrRoutingFilter was already registered under name: " + FILTER_NAME);
      return;
    }


    reg.addMappingForUrlPatterns(
        EnumSet.of(DispatcherType.REQUEST), /* isMatchAfter */ true, URL_PATTERN);

    reg.setAsyncSupported(true);

    ctx.log("CidrRoutingFilter registered programmatically for pattern: " + URL_PATTERN);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
  }
}
