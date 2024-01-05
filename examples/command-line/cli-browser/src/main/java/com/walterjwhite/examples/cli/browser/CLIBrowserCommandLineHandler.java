package com.walterjwhite.examples.cli.browser;

// import com.github.kwhat.jnativehook.GlobalScreen;
// import com.github.kwhat.jnativehook.NativeHookException;
// import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
// import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.walterjwhite.browser.api.authentication.AuthenticatedWebSession;
import com.walterjwhite.browser.api.authentication.WebsiteAuthenticator;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.serialization.api.service.SerializationService;
import java.io.FileInputStream;
import java.io.IOException;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CLIBrowserCommandLineHandler implements CommandLineHandler {
  protected final WebsiteAuthenticator websiteAuthenticator;
  //    protected final CreditService creditService;
  protected final SerializationService serializationService;

  @Inject
  public CLIBrowserCommandLineHandler(
      //      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      WebsiteAuthenticator websiteAuthenticator, SerializationService serializationService) {
    //    super(shutdownTimeoutInSeconds);

    this.websiteAuthenticator = websiteAuthenticator;
    //        this.creditService = creditService;
    this.serializationService = serializationService;
  }

  @Override
  public void run(String... arguments) throws Exception {
    // checkWeather();
    // creditService.getBalance(websiteAuthenticator.getBrowserService(), webSession));

    for (final String argument : arguments) {
      doLogin(argument);
    }
  }

  protected void doLogin(final String argument) throws Exception {
    final AuthenticatedWebSession webSession = getWebSession(argument);

    try {
      websiteAuthenticator.login(webSession);
      webSession.getWebDriver().waitToClose();
    } catch (Exception e) {
      LOGGER.error("error during login", e);
      Thread.sleep(5 * 60 * 1000);
    } finally {
      webSession.close();
    }
  }

  protected AuthenticatedWebSession getWebSession(final String argument) throws IOException {
    return serializationService.deserialize(
        new FileInputStream(argument), AuthenticatedWebSession.class);
  }

  //  protected void checkWeather() {
  //    webDriver.get("https://www.duckduckgo.com");
  //
  //    final WebElement search =
  //            webDriver.findElement(By.xpath("//*[@id=\"search_form_input_homepage\"]"));
  //    search.sendKeys("weather");
  //    search.submit();
  //
  //  }
}
