package com.walterjwhite.examples.serialization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(doNotUseGetters = true)
public class SwitchSetting {
  protected Switch aSwitch;
  protected String[] parameters;
}
