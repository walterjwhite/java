package com.walterjwhite.browser.api.authentication;

import com.walterjwhite.browser.api.model.WebSession;
import com.walterjwhite.browser.api.util.BrowserActionInstanceUtil;
import java.time.Duration;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
@NoArgsConstructor
public class AuthenticatedWebSession extends WebSession {
  protected WebCredentials webCredentials;
  protected Duration keepAliveDuration;
  // use the session timeout
  //    @EqualsAndHashCode.Exclude protected Duration keepAliveInterval;

  // applies currently to citrix web session
  protected String[] enabledFeatures;

  //  protected boolean logoutOnClose = true;

  @Override
  public void close() throws InterruptedException {
    if (webCredentials != null && webCredentials.getWebsite() != null) {
      BrowserActionInstanceUtil.execute(
          this, webDriver, webCredentials.getWebsite().getLogoutActions());
    }

    super.close();
  }
}
