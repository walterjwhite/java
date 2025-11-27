package com.walterjwhite.infrastructure.inject.core.locator;

import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
import java.util.Set;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ServiceLocator {
  public static <ServiceType, DataType> ServiceType getFirstSupported(
      Class<ServiceType> serviceTypeClass, final DataType data) {
    final Set<Class<? extends ServiceType>> services =
        ApplicationHelper.getApplicationInstance().getReflections().getSubTypesOf(serviceTypeClass);
    for (final Class<? extends ServiceType> serviceType : services) {
      final Supports supports = serviceType.getAnnotation(Supports.class);
      if (supports == null) {
        continue;
      }

      if (data.getClass().equals(supports.value())) {
        return ApplicationHelper.getApplicationInstance()
            .getInjector()
            .getInstance(serviceTypeClass);
      }
    }

    throw new IllegalArgumentException("Unsupported data type: " + data.getClass());
  }

  public static <ServiceType> ServiceType getFirstByName(
      Class<ServiceType> serviceTypeClass, final String serviceProviderName) {
    final Set<Class<? extends ServiceType>> services =
        ApplicationHelper.getApplicationInstance().getReflections().getSubTypesOf(serviceTypeClass);
    for (final Class<? extends ServiceType> serviceType : services) {
      if (serviceType.getSimpleName().equalsIgnoreCase(serviceProviderName)) {
        return ApplicationHelper.getApplicationInstance().getInjector().getInstance(serviceType);
      }
    }

    throw new IllegalArgumentException("Service Provider Not found: " + serviceProviderName);
  }
}
