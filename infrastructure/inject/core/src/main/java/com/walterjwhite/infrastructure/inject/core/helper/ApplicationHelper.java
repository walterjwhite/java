package com.walterjwhite.infrastructure.inject.core.helper;

import com.walterjwhite.infrastructure.inject.core.ApplicationInstance;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationHelper {

  public static transient ApplicationInstance APPLICATION_INSTANCE;

  public static void setApplicationInstance(ApplicationInstance applicationInstance) {
    APPLICATION_INSTANCE = applicationInstance;
  }

  public static ApplicationInstance getApplicationInstance() {
    return APPLICATION_INSTANCE;
  }
}
