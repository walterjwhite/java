package com.walterjwhite.transform.hash;

import lombok.Data;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
public class HashArgumentConfiguration extends HashResultConfiguration {
  protected int[] arguments;
}
