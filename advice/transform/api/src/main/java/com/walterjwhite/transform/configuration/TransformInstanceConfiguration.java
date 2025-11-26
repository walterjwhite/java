package com.walterjwhite.transform.configuration;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
@Data
public class TransformInstanceConfiguration implements Serializable {
  protected String targetClassName;
  protected String targetMethodName;
}
