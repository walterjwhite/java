package com.walterjwhite.serialization.modules.snakeymal;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@AllArgsConstructor
@NoArgsConstructor
public class TestSerialization implements Serializable {
  protected String firstName;
  protected String lastName;
  protected int age;
}
