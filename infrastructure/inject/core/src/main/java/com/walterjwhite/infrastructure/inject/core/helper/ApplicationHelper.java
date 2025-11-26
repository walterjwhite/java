package com.walterjwhite.infrastructure.inject.core.helper;

import com.walterjwhite.infrastructure.inject.core.ApplicationInstance;
import com.walterjwhite.infrastructure.inject.core.NodeId;
import com.walterjwhite.property.api.enumeration.ApplicationSCMVersion;
import com.walterjwhite.property.api.property.ApplicationEnvironment;
import com.walterjwhite.property.api.property.ApplicationManifestProperty;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import com.walterjwhite.property.modules.environment.EnvironmentPropertySource;
import com.walterjwhite.property.modules.manifest.ManifestPropertySource;
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
