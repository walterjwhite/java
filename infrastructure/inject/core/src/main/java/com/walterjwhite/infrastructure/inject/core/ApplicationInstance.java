package com.walterjwhite.infrastructure.inject.core;

import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
import com.walterjwhite.infrastructure.inject.core.helper.ApplicationInstanceBuilder;
import com.walterjwhite.infrastructure.inject.core.model.ApplicationSession;
import com.walterjwhite.infrastructure.inject.core.service.ServiceManager;
import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.property.api.SecretService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.reflections.Reflections;

@Getter
@RequiredArgsConstructor
public class ApplicationInstance {
  protected final Reflections reflections;
  protected final PropertyManager propertyManager;
  protected final ServiceManager serviceManager;
  protected final SecretService secretService;
  protected final Injector injector;

  protected final ApplicationSession applicationSession;

  public ApplicationInstance(
      Reflections reflections,
      PropertyManager propertyManager,
      ServiceManager serviceManager,
      SecretService secretService,
      Injector injector) {
    this(
        reflections,
        propertyManager,
        serviceManager,
        secretService,
        injector,
        ApplicationInstanceBuilder.build());
  }

  public void run() throws Exception {
    ApplicationHelper.setApplicationInstance(this);

    initialize();
    doRun();
  }

  protected void initialize() throws Exception {
    logApplicationIdentifier();

    propertyManager.initialize();
    injector.initialize();
    serviceManager.initialize();
  }

  protected String logApplicationIdentifier() {
    return applicationSession.toString();
  }

  protected void doRun() throws Exception {}
}
