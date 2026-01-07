package com.walterjwhite.web.servlet.request;

import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
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
import java.time.Instant;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RequestFilter implements Filter {
  private volatile RequestConsumer requestConsumer;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    requestConsumer =
        ApplicationHelper.getApplicationInstance().getInjector().getInstance(RequestConsumer.class);
  }

  @Override
  public void doFilter(
      final ServletRequest servletRequest,
      final ServletResponse servletResponse,
      final FilterChain filterChain)
      throws IOException, ServletException {

    if (!(servletRequest instanceof HttpServletRequest httpServletRequest)
        || !(servletResponse instanceof HttpServletResponse httpServletResponse)) {
      filterChain.doFilter(servletRequest, servletResponse);
      return;
    }

    try {
      filterChain.doFilter(httpServletRequest, httpServletResponse);
    } finally {
      requestConsumer.onRequest(fromHttpServletRequest(httpServletRequest));
    }
  }

  public static Request fromHttpServletRequest(final HttpServletRequest httpServletRequest) {
    return new Request(
        null,
        RequestHeaderUtil.getClientIp(httpServletRequest),
        Instant.now(),
        getUri(httpServletRequest),
        httpServletRequest.getHeader("User-Agent"));
  }

  public static final String getUri(final HttpServletRequest httpServletRequest) {
    final StringBuilder buffer = new StringBuilder();
    buffer.append(httpServletRequest.getRequestURI());
    if (httpServletRequest.getQueryString() != null) {
      buffer.append(httpServletRequest.getQueryString());
    }

    return buffer.toString();
  }

  @Override
  public void destroy() {
  }
}
