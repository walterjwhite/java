package com.walterjwhite.inject.cli;

import com.walterjwhite.infrastructure.inject.core.ApplicationArgumentAware;
import com.walterjwhite.infrastructure.inject.core.ApplicationInstance;
import com.walterjwhite.infrastructure.inject.core.Injector;
import com.walterjwhite.infrastructure.inject.core.service.ServiceManager;
import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.property.api.SecretService;
import com.walterjwhite.serialization.api.service.SerializationService;
import com.walterjwhite.serialization.modules.snakeyaml.Snakeyaml;
import java.lang.instrument.Instrumentation;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

@Slf4j
@Getter
public class AgentApplicationInstance extends ApplicationInstance
    implements ApplicationArgumentAware {
  protected final Instrumentation instrumentation;
  protected final String[] arguments;

  protected final transient SerializationService serializationService = new Snakeyaml();

  public AgentApplicationInstance(
      Reflections reflections,
      PropertyManager propertyManager,
      ServiceManager serviceManager,
      SecretService secretService,
      Injector injector,
      Instrumentation instrumentation,
      String arguments) {
    super(reflections, propertyManager, serviceManager, secretService, injector);
    if (arguments != null) {
      this.arguments = new String[] {arguments};
    } else {
      this.arguments = null;
    }

    this.instrumentation = instrumentation;
  }

  @Override
  protected void initialize() throws Exception {
    for (final Class<? extends AgentHandler> agentHandlerClass :
        reflections.getSubTypesOf(AgentHandler.class)) {
      final AgentHandler agentHandler = agentHandlerClass.getConstructor().newInstance();
      agentHandler.initialize(this);
    }

    super.initialize();
  }
}
