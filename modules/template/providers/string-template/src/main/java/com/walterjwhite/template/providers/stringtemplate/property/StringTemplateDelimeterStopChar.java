package com.walterjwhite.template.providers.stringtemplate.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface StringTemplateDelimeterStopChar extends ConfigurableProperty {
  @DefaultValue char Default = '$';
}
