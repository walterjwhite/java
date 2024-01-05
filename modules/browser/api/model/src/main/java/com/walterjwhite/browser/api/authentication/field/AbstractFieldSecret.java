package com.walterjwhite.browser.api.authentication.field;

import com.walterjwhite.browser.api.enumeration.WebElementBrowserAction;
import com.walterjwhite.browser.api.model.WebSession;
import com.walterjwhite.infrastructure.inject.core.Secret;
import lombok.Data;
import lombok.ToString;
import org.openqa.selenium.WebElement;

@ToString(doNotUseGetters = true)
@Data
public abstract class AbstractFieldSecret extends AbstractFieldEntry {
  public abstract Secret getSecret();

  @Override
  public WebElement enter(final WebSession webSession, final FieldPair fieldPair)
      throws InterruptedException {
    //    final WebElement webElement =
    //        LocatorUtil.locate(
    //            webSession.getBrowserActionDelayableMap().get(WebElementBrowserAction.Click),
    //            webSession.getWebDriver(),
    //            fieldPair.getInputLocator());
    //    WebDriverUtil.moveTo(
    //        webSession.getBrowserActionDelayableMap().get(BrowserAction.Click),
    //        webSession.getWebDriver(),
    //        webElement);
    //    WebDriverUtil.click(
    //        webSession.getBrowserActionDelayableMap().get(BrowserAction.Click), webElement);

    //    WebDriverUtil.type(
    //        webSession.getBrowserActionDelayableMap().get(BrowserAction.Type),
    //        webElement,
    //        getSecret().getValue(),
    //        true);

    return WebElementBrowserAction.Type.execute(
        webSession.getWebDriver(), webSession, fieldPair.getInputLocator(), getSecret().getValue());
    //    WebDriverUtil.sendKeys(
    //        webSession.getBrowserActionDelayableMap().get(BrowserAction.Type),
    //        webElement,
    //        getSecret().getValue(),
    //        false);

    //    return webElement;
  }
}
