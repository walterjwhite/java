package com.walterjwhite.identity.cli;

import lombok.Data;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
public class TokenConfiguration {
  protected boolean argumentKeyName;
  protected boolean useFirstArgument;
}
