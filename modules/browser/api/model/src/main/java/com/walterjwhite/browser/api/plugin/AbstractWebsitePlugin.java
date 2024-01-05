package com.walterjwhite.browser.api.plugin;

import com.walterjwhite.browser.api.authentication.AuthenticatedWebSession;
import com.walterjwhite.browser.api.authentication.WebsiteAuthenticator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractWebsitePlugin<SessionType extends AuthenticatedWebSession> {
  protected final WebsiteAuthenticator websiteAuthenticator;

  public void execute(final SessionType session) throws Exception {
    websiteAuthenticator.login(session);

    try {
      doExecute(session);
    } finally {
      websiteAuthenticator.logout(session);
    }
  }

  protected abstract void doExecute(final SessionType webSession) throws Exception;
}
