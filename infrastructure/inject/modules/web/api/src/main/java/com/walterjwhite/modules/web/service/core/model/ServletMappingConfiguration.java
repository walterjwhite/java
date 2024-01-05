package com.walterjwhite.modules.web.service.core.model;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;

@Getter
public class ServletMappingConfiguration {
  protected final Set<ServletMapping> servletMappings = new HashSet<>();
}
