package com.walterjwhite.browser.modules.ashot.renderer.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public enum AshotScreenshotOutputFormat implements ConfigurableProperty {
  @DefaultValue
  JPEG("jpg");

  protected final String extension;

  AshotScreenshotOutputFormat(String extension) {
    this.extension = extension;
  }

  public String getExtension() {
    return extension;
  }
}
