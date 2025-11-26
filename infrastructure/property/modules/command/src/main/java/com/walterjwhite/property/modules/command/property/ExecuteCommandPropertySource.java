package com.walterjwhite.property.modules.command.property;

import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.property.api.annotation.PropertySourceIndex;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import com.walterjwhite.property.impl.source.AbstractSingularStringPropertySource;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.apache.commons.io.IOUtils;

@PropertySourceIndex(1)
public class ExecuteCommandPropertySource
    extends AbstractSingularStringPropertySource<ConfigurableProperty> {
  public static String EXECUTE_COMMAND_EXECUTABLE = "/usr/bin/lookup-property";

  public ExecuteCommandPropertySource(PropertyManager propertyManager) {
    super(propertyManager, ConfigurableProperty.class);
  }

  protected String doGet(final String propertyKey) {
    try (final InputStream inputStream =
        Runtime.getRuntime()
            .exec(new String[] {EXECUTE_COMMAND_EXECUTABLE, propertyKey})
            .getInputStream()) {

      final String output = IOUtils.toString(inputStream, Charset.defaultCharset());
      if (output == null || output.isEmpty()) {
        return null;
      }

      return output.trim();
    } catch (IOException e) {
      throw new RuntimeException("Error getting value:", e);
    }
  }
}
