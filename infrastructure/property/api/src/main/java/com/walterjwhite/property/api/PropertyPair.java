package com.walterjwhite.property.api;

import com.walterjwhite.property.api.property.ConfigurableProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PropertyPair {
  protected final Class<? extends ConfigurableProperty> configurablePropertyClass;
  protected final String value;
}
