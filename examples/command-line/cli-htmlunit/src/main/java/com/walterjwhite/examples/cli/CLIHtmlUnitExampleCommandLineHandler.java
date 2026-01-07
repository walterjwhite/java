package com.walterjwhite.examples.cli;

import com.walterjwhite.citrix.*;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.property.api.annotation.Property;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CLIHtmlUnitExampleCommandLineHandler implements CommandLineHandler {
  protected final String username;
  protected final String password;
  protected final String pin;
  protected final String url;

  @Inject
  public CLIHtmlUnitExampleCommandLineHandler(
      @Property(CitrixUsername.class) final String username,
      @Property(CitrixPassword.class) final String password,
      @Property(CitrixPin.class) final String pin,
      @Property(CitrixUrl.class) final String url) {
    this.username = username;
    this.password = password;
    this.pin = pin;
    this.url = url;
  }

  @Override
  public void run(final String... arguments) throws Exception {
    final CitrixCredentials citrixCredentials = new CitrixCredentials();
    citrixCredentials.setUsername(username);
    citrixCredentials.setPassword(password);
    citrixCredentials.setToken(pin);
    citrixCredentials.setEphemeralToken(arguments[0]);

    final CitrixSession citrixSession = new CitrixSession();
    citrixSession.setUrl(url);
    citrixSession.setCitrixCredentials(citrixCredentials);
    citrixSession.setPageLoadTimeout(10_000);
    citrixSession.setLoginTimeout(10_000);

    final CitrixService citrixService = new CitrixServiceImpl();
    citrixService.run(citrixSession, new MouseWiggleExtension(citrixSession, 60_000, 0));
  }
}
