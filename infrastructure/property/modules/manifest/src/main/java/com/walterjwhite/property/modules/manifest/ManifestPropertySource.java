package com.walterjwhite.property.modules.manifest;

import com.jcabi.manifests.Manifests;
import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.property.api.annotation.PropertySourceIndex;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import com.walterjwhite.property.impl.source.AbstractSingularStringPropertySource;
import lombok.extern.slf4j.Slf4j;

@PropertySourceIndex(2)
@Slf4j
public class ManifestPropertySource extends AbstractSingularStringPropertySource {

  public ManifestPropertySource(PropertyManager propertyManager) {
    super(propertyManager, ConfigurableProperty.class);
  }

  @Override
  protected String doGet(final String lookupValue) {
    return get(lookupValue);
  }

  public static String get(final String lookupValue) {
    try {
      return Manifests.read(lookupValue);
    } catch (IllegalArgumentException e) {
      LOGGER.trace("Property not found:", lookupValue, e);
      return null;
    }
  }
}
