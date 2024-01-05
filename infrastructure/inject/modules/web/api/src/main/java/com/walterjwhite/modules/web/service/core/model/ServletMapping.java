package com.walterjwhite.modules.web.service.core.model;

import jakarta.servlet.Servlet;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
@RequiredArgsConstructor
public class ServletMapping {
  protected final String urlPattern;
  protected final Class<? extends Servlet> servletClass;
}
