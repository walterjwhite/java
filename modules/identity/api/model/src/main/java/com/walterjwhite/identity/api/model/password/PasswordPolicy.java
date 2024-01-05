package com.walterjwhite.identity.api.model.password;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
@Data
public class PasswordPolicy {
  protected int minimumLength;
  protected int maximumLength;
  protected CharacterSetConfiguration[] characterSetConfigurations;
}
