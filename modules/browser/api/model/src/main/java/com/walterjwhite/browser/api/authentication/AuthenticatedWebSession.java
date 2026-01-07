package com.walterjwhite.browser.api.authentication;

import com.walterjwhite.browser.api.model.WebSession;
import java.time.Duration;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
@NoArgsConstructor
@PersistenceCapable
public class AuthenticatedWebSession extends WebSession {
  protected WebCredentials webCredentials;
  protected Duration keepAliveDuration;

  protected String[] enabledFeatures;

}
