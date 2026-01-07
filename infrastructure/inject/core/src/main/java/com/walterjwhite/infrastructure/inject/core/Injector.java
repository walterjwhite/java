package com.walterjwhite.infrastructure.inject.core;

import com.walterjwhite.infrastructure.inject.core.locator.ServiceLocator;
import jakarta.enterprise.util.AnnotationLiteral;

public interface Injector {
  <T> T getInstance(Class<T> instanceClass, AnnotationLiteral... annotationLiterals);

  void initialize() throws Exception;

  default <T, DataType> T getFirstSupported(final Class<T> serviceTypeClass, final DataType data) {
    return ServiceLocator.getFirstSupported(serviceTypeClass, data);
  }

  default <ServiceType> ServiceType getFirstByName(
      Class<ServiceType> serviceTypeClass, final String serviceProviderName) {
    return ServiceLocator.getFirstByName(serviceTypeClass, serviceProviderName);
  }
}
