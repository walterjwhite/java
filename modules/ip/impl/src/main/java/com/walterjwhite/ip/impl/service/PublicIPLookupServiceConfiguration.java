package com.walterjwhite.ip.impl.service;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class PublicIPLookupServiceConfiguration {
  protected String provider;
}
