package com.walterjwhite.browser.api.authentication.field;

import com.walterjwhite.browser.api.enumeration.LocatorType;
import javax.jdo.annotations.PersistenceCapable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@PersistenceCapable
public class Locator {
  protected LocatorType locatorType;
  protected String argument;
}
