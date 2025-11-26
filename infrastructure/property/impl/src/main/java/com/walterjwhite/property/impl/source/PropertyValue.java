package com.walterjwhite.property.impl.source;

import com.walterjwhite.logging.annotation.Sensitive;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@RequiredArgsConstructor
public class PropertyValue {

  protected final Class propertyType;

  @Sensitive protected final String value;
}
