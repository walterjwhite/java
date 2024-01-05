package com.walterjwhite.web.financial.pnc;

import com.walterjwhite.browser.api.model.WebSession;
import com.walterjwhite.web.financial.api.CreditService;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class PNCCreditService implements CreditService {

  @Override
  public double getBalance(final WebSession webSession) {
    webSession.getWebDriver().switchTo().frame(1);

    //        return
    // Double.valueOf(webSession.getWebDriver().findElement(By.xpath("/html/body/div[5]/div[2]/div[3]/div[2]/div[2]/table/tbody/tr[2]/td[4]")).getText().substring(1));
    try {
      return Double.valueOf(
          webSession
              .getWebDriver()
              .findElement(By.xpath("//*[@id=\"cc_detail_row\"]/td[4]"))
              .getText()
              .substring(1));
    } catch (NoSuchElementException e) {
      throw new Error("Unable to determine balance", e);
    }
  }

  @Override
  public void payBalance(final WebSession webSession) {
    final double currentBalance = getBalance(webSession);
    if (currentBalance <= 0.0) {
      throw new IllegalStateException(
          String.format("Balance is currently < 0 (%d), unable to make a payment", currentBalance));
    }

    webSession.getWebDriver().switchTo().frame(0);

    webSession
        .getWebDriver()
        .findElement(
            By.xpath("/html/body/div[5]/div[2]/div[3]/div[2]/div[2]/table/tbody/tr[2]/td[1]/a"))
        .click();

    // make payment
    webSession.getWebDriver().switchTo().frame(0);
    webSession.getWebDriver().findElement(By.xpath("//*[@id=\"makePayment\"]/a")).click();

    webSession.getWebDriver().switchTo().frame(0);
    webSession.getWebDriver().findElement(By.xpath("//*[@id=\"fromAcctId\"]")).click();
  }
}
