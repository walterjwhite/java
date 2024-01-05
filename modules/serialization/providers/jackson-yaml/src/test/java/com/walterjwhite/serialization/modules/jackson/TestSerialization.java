package com.walterjwhite.serialization.modules.jackson;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import java.time.LocalDateTime;
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
public class TestSerialization extends AbstractEntity {
  @EqualsAndHashCode.Exclude protected TestSerializationKey id;
  protected int age;
  protected LocalDateTime creationDateTime;

  // clients constructing this object now will have to do this manually
  // this.id = new TestSerializationKey(firstName, lastName);
}
