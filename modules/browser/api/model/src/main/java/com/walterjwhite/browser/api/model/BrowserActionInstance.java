package com.walterjwhite.browser.api.model;

import com.walterjwhite.browser.api.enumeration.LocatorType;
import com.walterjwhite.browser.api.enumeration.WebElementBrowserAction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
@Data
public class BrowserActionInstance {
  protected WebElementBrowserAction browserAction;
  protected LocatorType locatorType;
  protected String locatorArgument;
  protected Object[] arguments;
}
