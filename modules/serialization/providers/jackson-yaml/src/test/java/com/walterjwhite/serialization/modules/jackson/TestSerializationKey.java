package com.walterjwhite.serialization.modules.jackson;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true)
@Embeddable
public class TestSerializationKey implements Serializable {
  //
  protected String firstName;

  //
  protected String lastName;

  public TestSerializationKey(String firstName, String lastName) {

    this.firstName = firstName;
    this.lastName = lastName;
  }

  public TestSerializationKey() {}
}
