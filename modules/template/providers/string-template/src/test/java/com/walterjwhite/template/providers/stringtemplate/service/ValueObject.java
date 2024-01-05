package com.walterjwhite.template.providers.stringtemplate.service;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class ValueObject {
  protected String name;
}
