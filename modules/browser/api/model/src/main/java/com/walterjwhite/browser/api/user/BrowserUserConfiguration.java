package com.walterjwhite.browser.api.user;

import com.walterjwhite.browser.api.enumeration.WebElementBrowserAction;
import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import java.time.Duration;
import java.util.Map;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString(doNotUseGetters = true)
@Data
public class BrowserUserConfiguration extends AbstractEntity {
  @EqualsAndHashCode.Exclude protected Duration elementLocatorWaitDuration;

  @EqualsAndHashCode.Exclude
  protected Map<WebElementBrowserAction, BrowserRandomDelayable> browserActionDelayableMap;

  @EqualsAndHashCode.Exclude protected boolean simulateUser;
}
