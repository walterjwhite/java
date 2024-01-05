package com.walterjwhite.spring.web.logging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExceptionLoggingFilterConfig {
  @Bean
  public ExceptionLoggingFilter getLogFilter() {
    final ExceptionLoggingFilter filter = new ExceptionLoggingFilter();

    // todo: configure dynamically
    filter.setIncludeQueryString(true);
    filter.setIncludePayload(true);
    filter.setMaxPayloadLength(10000);
    filter.setIncludeHeaders(true);
    filter.setBeforeMessagePrefix("");
    filter.setAfterMessagePrefix("");

    return filter;
  }
}
