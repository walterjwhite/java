package com.walterjwhite.index.api.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public enum IndexNameType implements ConfigurableProperty {
  @DefaultValue
  Default() {
    public String getName(final String indexPrefix, final Class<?> entityClass) {
      return indexPrefix + "." + entityClass.getSimpleName().toLowerCase();
    }
  },
  LongName() {
    public String getName(final String indexPrefix, final Class<?> entityClass) {
      return indexPrefix + "." + entityClass.getName().toLowerCase();
    }
  };

  public abstract String getName(final String indexPrefix, final Class<?> entityClass);
}
