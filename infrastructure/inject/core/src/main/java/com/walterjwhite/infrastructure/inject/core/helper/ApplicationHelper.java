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

// we can use reflection to get them
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationHelper {

  public static transient ApplicationInstance APPLICATION_INSTANCE;

  public static void setApplicationInstance(ApplicationInstance applicationInstance) {
    APPLICATION_INSTANCE = applicationInstance;
  }

  public static ApplicationInstance getApplicationInstance() {
    return APPLICATION_INSTANCE;
  }

  public static String getApplicationName() {
    return ApplicationHelper.class.getPackage().getImplementationTitle();
  }

  public static String getImplementationVersion() {
    return ApplicationInstance.class.getPackage().getImplementationVersion();
  }

  public static String getSCMVersion() {
    return getManifestProperty(ApplicationSCMVersion.class);
  }

  // the environment the manifest indicates
  public static ApplicationEnvironment getApplicationTargetEnvironment() {
    return ApplicationEnvironment.valueOf(getManifestProperty(ApplicationEnvironment.class));
  }

  public static ApplicationEnvironment getApplicationEnvironment() {
    // return getOrDefault(ApplicationEnvironment.class, ApplicationEnvironment.Development);
    final String value = EnvironmentPropertySource.get(lookup(ApplicationEnvironment.class));
    if (value == null) {
      return ApplicationEnvironment.Development;
    }

    return ApplicationEnvironment.valueOf(value);
  }

  public static String getNodeId() {
    // return EnvironmentPropertySource.get(lookup(NodeId.class));
    // return getOrDefault(NodeId.class, "local");
    final String value = EnvironmentPropertySource.get(lookup(NodeId.class));
    if (value == null) {
      return "local";
    }

    return value;
  }

  //  private static <ValueType> getOrDefault(final Class<? extends ConfigurableProperty>
  // propertyType, final ValueType defaultValue){
  //    final String environmentValue = EnvironmentPropertySource.get(lookup(propertyType));
  //    if(environmentValue != null)
  //      return (ValueType) Enum.valueOf((Class<? extends Enum>) propertyType, environmentValue);
  //
  //    return defaultValue;
  //  }

  public static String getManifestProperty(
      final Class<? extends ApplicationManifestProperty> applicationManifestProperty) {
    return ManifestPropertySource.get(applicationManifestProperty.getSimpleName());
  }

  private static String lookup(
      final Class<? extends ConfigurableProperty> configurablePropertyClass) {
    return configurablePropertyClass.getName();
  }
}
