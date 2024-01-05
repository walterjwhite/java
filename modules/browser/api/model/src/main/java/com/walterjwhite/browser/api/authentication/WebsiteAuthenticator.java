package com.walterjwhite.browser.api.authentication;

import com.walterjwhite.browser.api.authentication.field.FieldGroup;
import com.walterjwhite.browser.api.authentication.field.FieldPair;
import com.walterjwhite.browser.api.enumeration.WebElementBrowserAction;
import com.walterjwhite.browser.api.service.SessionAwareWebDriver;
import com.walterjwhite.browser.api.util.BrowserActionInstanceUtil;
import com.walterjwhite.browser.api.util.FieldPairUtil;
import com.walterjwhite.browser.api.util.WebDriverUtil;
import com.walterjwhite.browser.api.util.WebsiteReader;
import com.walterjwhite.keep_alive.KeepAliveable;
import java.io.IOException;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;

@Slf4j
@Getter
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class WebsiteAuthenticator implements KeepAliveable<AuthenticatedWebSession> {
  protected final Provider<SessionAwareWebDriver> webDriverProvider;
  protected final WebsiteReader websiteReader;
  //  protected final TokenService tokenService;

  //  @HeartbeatLock
  public void login(final AuthenticatedWebSession webSession) throws Exception {
    init(webSession);
    doPreAuthenticationActions(webSession);

    doLogin(webSession);
    if (!isLoggedIn(webSession)) {
      // wait 30s for user to complete, then continue
      Thread.sleep(30000);

      //      throw new IllegalStateException(
      //          "Unable to authenticate:\n" + webSession.getWebDriver().getPageSource());
    }

    doPostAuthenticationActions(webSession);
  }

  protected void init(final AuthenticatedWebSession webSession) throws IOException {
    websiteReader.initWebsite(webSession);

    webSession.setWebDriver(webDriverProvider.get());
    webSession.getWebDriver().setWebSession(webSession);

    //    webSession.getWebDriver().manage();
    LOGGER.warn(
        "Browser Version:"
            + webSession.getWebDriver().getWebDriverCapabilities().getBrowserVersion());
  }

  protected void doPreAuthenticationActions(final AuthenticatedWebSession webSession)
      throws InterruptedException {
    BrowserActionInstanceUtil.execute(
        webSession,
        webSession.getWebDriver(),
        webSession.getWebCredentials().getWebsite().getPreAuthenticationActions());
  }

  protected void doPostAuthenticationActions(final AuthenticatedWebSession webSession)
      throws InterruptedException {
    BrowserActionInstanceUtil.execute(
        webSession,
        webSession.getWebDriver(),
        webSession.getWebCredentials().getWebsite().getPostAuthenticationActions());
  }

  protected void doLogin(final AuthenticatedWebSession webSession) throws InterruptedException {
    webSession.getWebDriver().get(webSession.getWebCredentials().getWebsite().getUri());

    for (final FieldGroup fieldGroup :
        webSession.getWebCredentials().getWebsite().getFieldGroups()) {

      int index = 0;
      for (final FieldPair fieldPair : fieldGroup.getFieldPairs()) {
        // check if the field pair is located *AND* it is required, if not, then skip
        if (fieldPair.isRequired()) {
          if (webSession.getElementLocatorWaitDuration() != null) {
            WebDriverUtil.waitUntilFound(
                webSession.getWebDriver(),
                fieldPair
                    .getInputLocator()
                    .getLocatorType()
                    .get(fieldPair.getInputLocator().getArgument()),
                webSession.getElementLocatorWaitDuration());
          }
        }

        //          if(fieldPair.getFieldProcessorClass() != null) {
        //            FieldProcessor fieldProcessor =
        // ApplicationHelper.getApplicationInstance().getInjector().getInstance(fieldPair.getFieldProcessorClass());
        //            fieldProcessor.get(fieldPair, fieldSecret);
        //          }

        final WebElement webElement =
            FieldPairUtil.get(fieldPair, webSession.getWebCredentials())
                .enter(webSession, fieldPair);

        if (isLastElementInFieldPair(fieldGroup, index)) {
          submitFieldPair(webSession, fieldGroup, webElement);
        }

        index++;
      }
    }
  }

  protected boolean isLastElementInFieldPair(final FieldGroup fieldGroup, final int index) {
    return index == (fieldGroup.getFieldPairs().length - 1);
  }

  protected void submitFieldPair(
      final AuthenticatedWebSession webSession,
      final FieldGroup fieldGroup,
      final WebElement webElement)
      throws InterruptedException {
    if (fieldGroup.getSubmitLocator() != null) {
      //            WebElementBrowserAction.MoveTo.execute(webSession.getWebDriver(),
      // webSession, submitElement);
      WebElementBrowserAction.Click.execute(
          webSession.getWebDriver(), webSession, fieldGroup.getSubmitLocator());
    } else {
      webElement.submit();
    }
  }

  protected boolean isLoggedIn(final AuthenticatedWebSession webSession) {
    if (webSession.getWebCredentials().getWebsite().getLoggedInLocator() == null) {
      return true;
    }

    return WebDriverUtil.isPresent(
        webSession, webSession.getWebCredentials().getWebsite().getLoggedInLocator());
  }

  //  @HeartbeatCancel
  public void logout(AuthenticatedWebSession webSession) throws InterruptedException {
    BrowserActionInstanceUtil.execute(
        webSession,
        webSession.getWebDriver(),
        webSession.getWebCredentials().getWebsite().getLogoutActions());
  }

  // merely fetch the main page again is usually enough to keep the session alive
  @Override
  public void onKeepAlive(AuthenticatedWebSession data) {
    data.getWebDriver().get(data.getWebCredentials().getWebsite().getUri());
  }
}
