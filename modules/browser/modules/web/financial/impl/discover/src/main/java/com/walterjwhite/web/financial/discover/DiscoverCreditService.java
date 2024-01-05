package com.walterjwhite.web.financial.discover;

import com.walterjwhite.browser.api.model.WebSession;
import com.walterjwhite.browser.api.util.WebDriverUtil;
import com.walterjwhite.web.financial.api.CreditService;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class DiscoverCreditService implements CreditService {

  @Override
  public double getBalance(WebSession webSession) {
    try {
      return Double.valueOf(
          webSession
              .getWebDriver()
              .findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div[3]/div/div[2]/strong"))
              .getText()
              .substring(1));
    } catch (NoSuchElementException e) {
      WebDriverUtil.logException(webSession, e);
      throw new Error("Unable to determine balance", e);
    }
  }

  @Override
  public void payBalance(final WebSession webSession) {}
}
