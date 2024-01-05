package com.walterjwhite.serialization.modules.snakeymal;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@AllArgsConstructor
@NoArgsConstructor
public class TestSerialization extends AbstractEntity {
  protected String firstName;
  protected String lastName;
  protected int age;
}
