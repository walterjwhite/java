package com.walterjwhite.property.modules.file;

import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.property.api.annotation.PropertySourceIndex;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import com.walterjwhite.property.impl.source.AbstractSingularStringPropertySource;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.commons.io.IOUtils;

@PropertySourceIndex(3)
public class FilePropertySource extends AbstractSingularStringPropertySource<ConfigurableProperty> {
  public static final String FILE_PATH = "/run/configuration";

  protected final boolean enabled;

  public FilePropertySource(PropertyManager propertyManager) {
    super(propertyManager, ConfigurableProperty.class);

    enabled = new File(FILE_PATH).exists();
  }

  @Override
  protected String doGet(String propertyKey) {
    if (!enabled) {
      return null;
    }

    try {
      return IOUtils.toString(
          new BufferedInputStream(
              new FileInputStream(FILE_PATH + propertyKey.replaceAll("\\.", File.separator))),
          Charset.defaultCharset());
    } catch (IOException e) {
      return null;
    }
  }
}
