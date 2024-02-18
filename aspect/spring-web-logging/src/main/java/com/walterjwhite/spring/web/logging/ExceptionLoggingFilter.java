package com.walterjwhite.spring.web.logging;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

@Getter
public class ExceptionLoggingFilter extends CommonsRequestLoggingFilter {
  protected final ThreadLocal<String> requestThreadLocal = new ThreadLocal<>();
  protected final ThreadLocal<ContentCachingRequestWrapper> requestWrapperThreadLocal =
      new ThreadLocal<>();

  @Override
  protected boolean shouldLog(HttpServletRequest request) {
    return true;
  }

  @Override
  protected void beforeRequest(HttpServletRequest request, String message) {
    requestThreadLocal.set(message);
  }

  @Override
  protected String getMessagePayload(HttpServletRequest request) {
    final ContentCachingRequestWrapper wrapper =
        WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
    if (wrapper != null) {
      requestWrapperThreadLocal.set(wrapper);
    }

    return super.getMessagePayload(request);
  }
}
