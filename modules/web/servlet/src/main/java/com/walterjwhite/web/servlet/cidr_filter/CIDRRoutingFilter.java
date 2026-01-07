package com.walterjwhite.web.servlet.cidr_filter;

import com.walterjwhite.web.servlet.RequestHeaderUtil;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CIDRRoutingFilter implements Filter {
  private static final String DEFAULT_RULES_LOCATION = "/cidr-routing.properties";
  private final List<CIDRRule> rules = new ArrayList<>();

  @Override
  public void init(final FilterConfig filterConfig) {
    CIDRRuleLoader.load(getRulesLocation(filterConfig), rules);
  }

  private String getRulesLocation(final FilterConfig filterConfig) {
    final String rulesLocation = filterConfig.getInitParameter("rules.location");
    if (rulesLocation != null && rulesLocation.isEmpty()) {
      return rulesLocation;
    }

    return DEFAULT_RULES_LOCATION;
  }

  @Override
  public void doFilter(
      final ServletRequest request, final ServletResponse response, final FilterChain chain)
      throws IOException, ServletException {
    if (!(request instanceof HttpServletRequest httpServletRequest)
        || !(response instanceof HttpServletResponse httpServletResponse)) {
      chain.doFilter(request, response);
      return;
    }

    final String clientIp = RequestHeaderUtil.getClientIp(httpServletRequest);
    if (clientIp == null) {
      LOGGER.warn("clientIP is null");
      chain.doFilter(request, response);
      return;
    }

    for (CIDRRule r : rules) {
      if (r.getCidrMatcher().matches(clientIp)) {
        LOGGER.debug("Client IP {}, matched rule {}", clientIp, r);
        r.getRoutingAction().doFilter(r, httpServletRequest, httpServletResponse, chain);
        return;
      }
    }

    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {
    rules.clear();
  }
}
