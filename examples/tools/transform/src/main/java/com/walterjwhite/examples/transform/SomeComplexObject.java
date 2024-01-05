package com.walterjwhite.examples.transform;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
@Data
public class SomeComplexObject implements Serializable {
  protected String name;

  protected Address address;
}
