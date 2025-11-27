package com.walterjwhite.transform.rot13_5;

import com.walterjwhite.transform.configuration.TransformInstanceConfiguration;
import lombok.Data;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
public class ROT13_5ArgumentConfiguration extends TransformInstanceConfiguration {
  protected int[] arguments;
}
