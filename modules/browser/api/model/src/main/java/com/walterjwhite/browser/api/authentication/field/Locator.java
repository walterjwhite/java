package com.walterjwhite.browser.api.authentication.field;

import com.walterjwhite.browser.api.enumeration.LocatorType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Locator {
  protected LocatorType locatorType;
  protected String argument;
}
