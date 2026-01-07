package com.walterjwhite.web.servlet.cidr_filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum RoutingAction {
  CONTINUE {
    @Override
    public void doFilter(
        final CIDRRule cidrRule,
        final HttpServletRequest request,
        final HttpServletResponse response,
        final FilterChain chain)
        throws IOException, ServletException {
      chain.doFilter(request, response);
    }
  },
  REDIRECT {
    @Override
    public void doFilter(
        final CIDRRule cidrRule,
        final HttpServletRequest request,
        final HttpServletResponse response,
        final FilterChain chain)
        throws IOException, ServletException {
      if (cidrRule.getTarget() == null || cidrRule.getTarget().isEmpty()) {
        LOGGER.warn("Redirect rule without target for rule {}. Continuing request.", cidrRule);
        chain.doFilter(request, response);
        return;
      }

      response.sendRedirect(cidrRule.getTarget());
    }
  };

  public abstract void doFilter(
      final CIDRRule cidrRule,
      final HttpServletRequest request,
      final HttpServletResponse response,
      final FilterChain chain)
      throws IOException, ServletException;
}
