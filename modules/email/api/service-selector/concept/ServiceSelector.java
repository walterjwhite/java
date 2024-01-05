package com.walterjwhite.email.api.service.selector.concept;

import com.walterjwhite.infrastructure.inject.core.Injector;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import org.reflections.Reflections;

/** Only allow a single implementation for a given type (ie. JavaMail / Exchange) */
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class ServiceSelector {
  protected final Reflections reflections;
  protected final Injector injector;

  public <ArgumentType, ServiceType> ServiceType getInstance(
      final Class<? extends ServiceType> serviceTypeClass,
      final Class<? extends ProviderType> providerTypeClass,
      final ArgumentType argument) {
    return getInstance(
        serviceTypeClass, getProviderType(argument, providerTypeClass).getProviderType(argument));
  }

  protected <ServiceType> ServiceType getInstance(
      final Class<? extends ServiceType> serviceTypeClass, final ProviderType providerType) {
    for (final Class<? extends ServiceType> serviceClass :
        reflections.getSubTypesOf(serviceTypeClass)) {
      final ServiceSupports serviceSupports = serviceClass.getAnnotation(ServiceSupports.class);
      if (serviceSupports == null) {
        throw new IllegalStateException(
            String.format(
                "Service is not properly configured: %s (%s)", serviceTypeClass, serviceClass));
      }

      if (providerType.equals(serviceSupports.value())) {
        return injector.getInstance(serviceClass);
      }
    }

    throw new IllegalArgumentException("Unable to find a provider for: " + providerType);
  }

  protected <ArgumentType> ProviderTypeMap getProviderType(
      final ArgumentType argument, final Class<? extends ProviderType> providerTypeClass) {
    for (final Class<? extends ProviderTypeMap> providerTypeMapClass :
        reflections.getSubTypesOf(ProviderTypeMap.class)) {
      final ProviderMapSupports supports =
          providerTypeMapClass.getAnnotation(ProviderMapSupports.class);
      if (supports == null) {
        throw new IllegalStateException(
            String.format("ProviderTypeMap is not properly configured: %s", providerTypeMapClass));
      }

      if (supports.argumentType().equals(argument.getClass())
          && supports.providerType().equals(providerTypeClass)) {
        return injector.getInstance(providerTypeMapClass);
      }
    }

    throw new IllegalArgumentException("Unable to find a provider for: " + argument);
  }
}
