package com.walterjwhite.identity.api.model.password;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
@Data
public class CharacterSetConfiguration {
  protected int number;
  protected CharacterClass characterClass;
  protected String characters;
}
