package com.walterjwhite.property.modules.environment;

import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.property.api.annotation.PropertySourceIndex;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import com.walterjwhite.property.impl.source.AbstractSingularStringPropertySource;

@PropertySourceIndex(2)
public class EnvironmentPropertySource extends AbstractSingularStringPropertySource {
  public EnvironmentPropertySource(PropertyManager propertyManager) {
    super(propertyManager, ConfigurableProperty.class);
  }

  @Override
  protected String doGet(final String lookupValue) {
    return get(lookupValue);
  }

  public static String get(final String lookupValue) {
    return System.getenv().get(lookup(lookupValue));
  }

  // environment variables in linux may not have a "."
  public static String lookup(final String lookupValue) {
    return (lookupValue.replaceAll("\\.", "_"));
  }
}
