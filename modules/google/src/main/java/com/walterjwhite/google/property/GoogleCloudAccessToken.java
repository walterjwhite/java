package com.walterjwhite.google.property;

import com.walterjwhite.logging.annotation.Sensitive;
import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

@Sensitive
public interface GoogleCloudAccessToken extends ConfigurableProperty {
  @DefaultValue String Default = "accessToken.json";
}
