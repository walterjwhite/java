package com.walterjwhite.serialization.modules.jackson;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "class")
public class TestSerialization implements Serializable {
  @EqualsAndHashCode.Exclude protected String firstName;
  protected String lastName;
  protected int age;

}
