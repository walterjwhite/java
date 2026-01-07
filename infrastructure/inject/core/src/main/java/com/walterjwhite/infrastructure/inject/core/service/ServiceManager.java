package com.walterjwhite.infrastructure.inject.core.service;

import com.walterjwhite.closeable.runtime.CloseableUtil;
import com.walterjwhite.infrastructure.inject.core.Injector;
import com.walterjwhite.property.impl.PropertyHelper;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

@Slf4j
@RequiredArgsConstructor
public class ServiceManager {
  protected final Reflections reflections;
  protected final Injector injector;


  public void initialize() {
    getStartupAwareServices().forEach(service -> doStartService(service));
  }

  protected Set<Class<? extends StartupAware>> getStartupAwareServices() {
    return reflections.getSubTypesOf(StartupAware.class).stream()
        .filter(serviceClass -> PropertyHelper.isConcrete(serviceClass))
        .collect(Collectors.toSet());
  }

  @SneakyThrows
  protected void doStartService(final Class<? extends StartupAware> serviceClass) {
    final StartupAware startupAware = injector.getInstance(serviceClass);
    startupAware.startup();
    CloseableUtil.addAutoCloseable(startupAware);
  }

  protected Set<Class<? extends ShutdownAware>> gethutdownAwareServices() {
    return reflections.getSubTypesOf(ShutdownAware.class).stream()
        .filter(serviceClass -> PropertyHelper.isConcrete(serviceClass))
        .collect(Collectors.toSet());
  }

  @SneakyThrows
  protected void doStopService(final Class<? extends ShutdownAware> serviceClass) {
    final ShutdownAware shutdownAware = injector.getInstance(serviceClass);
    shutdownAware.shutdown();
  }
}
