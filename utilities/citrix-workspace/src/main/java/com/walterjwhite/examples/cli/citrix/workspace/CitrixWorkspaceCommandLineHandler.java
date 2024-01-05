package com.walterjwhite.examples.cli.citrix.workspace;

import com.walterjwhite.browser.api.authentication.AuthenticatedWebSession;
import com.walterjwhite.browser.api.authentication.WebsiteAuthenticator;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;
import jakarta.inject.Provider;

public class CitrixWorkspaceCommandLineHandler implements CommandLineHandler {
  protected final WebsiteAuthenticator websiteAuthenticator;
  protected final Provider<AuthenticatedWebSession> webSessionProvider;

  @Inject
  public CitrixWorkspaceCommandLineHandler(
      //      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      final WebsiteAuthenticator websiteAuthenticator,
      final Provider<AuthenticatedWebSession> webSessionProvider) {
    //    super(shutdownTimeoutInSeconds);
    this.websiteAuthenticator = websiteAuthenticator;
    this.webSessionProvider = webSessionProvider;
  }

  @Override
  public void run(String... arguments) throws Exception {
    websiteAuthenticator.login(webSessionProvider.get());

    // I want to ensure the deserialization works propertly first ...
  }
}
