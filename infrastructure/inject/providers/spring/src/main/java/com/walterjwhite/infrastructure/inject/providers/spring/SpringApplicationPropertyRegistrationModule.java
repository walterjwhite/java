package com.walterjwhite.infrastructure.inject.providers.spring;

import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
import com.walterjwhite.property.api.PropertyManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SpringApplicationPropertyRegistrationModule {
  protected final SpringApplicationModule springApplicationModule;
  protected transient PropertyManager propertyManager;

  protected void configure() {
    propertyManager = ApplicationHelper.getApplicationInstance().getPropertyManager();
  }
}
