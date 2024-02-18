package com.walterjwhite.browser.api.user;

import com.walterjwhite.browser.api.enumeration.WebElementBrowserAction;

import java.time.Duration;
import java.util.Map;
import lombok.*;

import javax.jdo.annotations.PersistenceCapable;

@AllArgsConstructor
@NoArgsConstructor
@ToString(doNotUseGetters = true)
@Data
@PersistenceCapable
public class BrowserUserConfiguration {
  @EqualsAndHashCode.Exclude protected Duration elementLocatorWaitDuration;

  @EqualsAndHashCode.Exclude
  protected Map<WebElementBrowserAction, BrowserRandomDelayable> browserActionDelayableMap;

  @EqualsAndHashCode.Exclude protected boolean simulateUser;
}
