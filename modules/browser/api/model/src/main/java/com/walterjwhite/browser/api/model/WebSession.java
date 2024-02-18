package com.walterjwhite.browser.api.model;

import com.walterjwhite.browser.api.user.BrowserUserConfiguration;
import com.walterjwhite.keep_alive.KeepAlive;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.jdo.annotations.PersistenceCapable;
import java.time.LocalDateTime;

@ToString(doNotUseGetters = true)
@Data
@NoArgsConstructor
@PersistenceCapable
public class WebSession extends BrowserUserConfiguration implements KeepAlive {
  protected LocalDateTime startDateTime = LocalDateTime.now();

  protected String websiteIdentifier;
  protected boolean waitForUserToClose;

}
