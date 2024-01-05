package com.walterjwhite.infrastructure.inject.core.service;

import com.walterjwhite.closeable.impl.CloseableUtil;
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

  // @TODO: consider re-instating @ServiceStopTimeout.class
  //  @Property(ServiceStopTimeout.class)
  // protected final int serviceStopTimeout = 30;

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
}
