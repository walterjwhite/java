package com.walterjwhite.property.modules.properties;

import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.property.api.annotation.PropertySourceIndex;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import com.walterjwhite.property.impl.source.AbstractSingularStringPropertySource;
import java.net.URL;
import java.util.Properties;

@PropertySourceIndex(4)
public class PropertiesPropertySource
    extends AbstractSingularStringPropertySource<ConfigurableProperty> {
  public static final String APPLICATION_PROPERTIES_FILE_PATH = "/application.properties";

  protected final Properties loadedProperties;

  public PropertiesPropertySource(PropertyManager propertyManager) {
    super(propertyManager, ConfigurableProperty.class);

    loadedProperties = loadFromPropertiesFile();
  }

  protected Properties loadFromPropertiesFile() {
    try {
      final Properties tempProperties = new Properties();
      URL url = PropertyManager.class.getResource(APPLICATION_PROPERTIES_FILE_PATH);

      if (url != null) {
        tempProperties.load(url.openStream());
        return tempProperties;
      } else handlePropertiesFileDoesNotExist();
    } catch (Exception e) {
      handleLoadException(e);
    }

    return null;
  }

  protected void handlePropertiesFileDoesNotExist() {}

  protected void handleLoadException(Exception e) {}

  @Override
  protected String doGet(String propertyKey) {
    if (loadedProperties != null) {
      return loadedProperties.getProperty(propertyKey);
    }

    return null;
  }
}
