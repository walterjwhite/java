package com.walterjwhite.property.validation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Attribute {
  protected final String name;
  protected final String typeName;
}
