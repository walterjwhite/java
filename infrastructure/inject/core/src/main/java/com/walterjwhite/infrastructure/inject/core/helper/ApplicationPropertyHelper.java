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
public class ApplicationPropertyHelper {
  public static String getApplicationName() {
    return ApplicationHelper.class.getPackage().getImplementationTitle();
  }

  public static String getImplementationVersion() {
    return ApplicationInstance.class.getPackage().getImplementationVersion();
  }

  public static String getSCMVersion() {
    return getManifestProperty(ApplicationSCMVersion.class);
  }

  public static ApplicationEnvironment getApplicationTargetEnvironment() {
    return ApplicationEnvironment.valueOf(getManifestProperty(ApplicationEnvironment.class));
  }

  public static ApplicationEnvironment getApplicationEnvironment() {
    final String value = EnvironmentPropertySource.get(lookup(ApplicationEnvironment.class));
    if (value == null) {
      return ApplicationEnvironment.Development;
    }

    return ApplicationEnvironment.valueOf(value);
  }

  public static String getNodeId() {
    final String value = EnvironmentPropertySource.get(lookup(NodeId.class));
    if (value == null) {
      return "local";
    }

    return value;
  }


  public static String getManifestProperty(
      final Class<? extends ApplicationManifestProperty> applicationManifestProperty) {
    return ManifestPropertySource.get(applicationManifestProperty.getSimpleName());
  }

  private static String lookup(
      final Class<? extends ConfigurableProperty> configurablePropertyClass) {
    return configurablePropertyClass.getName();
  }
}
