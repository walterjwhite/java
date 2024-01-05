package com.walterjwhite.property.api;

public interface PropertyNameLookupService {
  default String lookup(Class propertyClass) {
    return propertyClass.getName();
  }
}
