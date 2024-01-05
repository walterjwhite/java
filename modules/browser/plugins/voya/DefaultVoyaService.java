package com.walterjwhite.browser.plugins.voya.service;

import com.walterjwhite.browser.plugins.voya.api.model.VoyaCredentials;
import com.walterjwhite.browser.plugins.voya.api.property.VoyaUrl;
import com.walterjwhite.browser.plugins.voya.api.service.VoyaPlugin;
import com.walterjwhite.browser.plugins.voya.api.service.VoyaService;
import com.walterjwhite.identity.api.service.TokenService;
import com.walterjwhite.property.impl.annotation.Property;
import jakarta.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class DefaultVoyaService implements VoyaService {
  protected final BrowserService browserService;

  protected final String voyaUrl;
  protected final TokenService tokenService;

  @Inject
  public DefaultVoyaService(
      BrowserService browserService,
      @Property(VoyaUrl.class) final String voyaUrl,
      TokenService tokenService) {

    this.browserService = browserService;
    this.voyaUrl = voyaUrl;
    this.tokenService = tokenService;
  }

  @Override
  public void execute(VoyaCredentials voyaCredentials, VoyaPlugin voyaPlugin)
      throws InterruptedException {
    try {
      login(voyaCredentials);

      voyaPlugin.setBrowserService(browserService);
      voyaPlugin.execute();

      logout();
    } catch (NoSuchElementException e) {
      WebDriverUtil.logException(webSession, e);
      throw e;
    }
  }

  public void login(VoyaCredentials voyaCredentials) throws InterruptedException {
    navigateToVoya();
    enterUsername(voyaCredentials);
    enterPassword(voyaCredentials);
    submitAuthentication();
    handle2FactorAuthentication();

    // updateAccountBalances();
  }

  protected void navigateToVoya() {
    browserService.execute(new Get(voyaUrl));
  }

  protected void enterUsername(VoyaCredentials voyaCredentials) {
    browserService
        .getWebDriver()
        .findElement(By.id("emailOrUsername"))
        .sendKeys(voyaCredentials.getUsername().getPlainText());
  }

  protected void enterPassword(VoyaCredentials voyaCredentials) {
    final WebElement passwordElement = browserService.getWebDriver().findElement(By.id("password"));
    passwordElement.sendKeys(voyaCredentials.getPassword().getPlainText());
  }

  protected void submitAuthentication() {
    browserService
        .getWebDriver()
        .findElement(
            By.xpath(
                "//*[@id=\"doc-main-inner\"]/compose/div/div[1]/login-block/form/div/voya-button/span"))
        .click();
  }

  protected void handle2FactorAuthentication() throws InterruptedException {
    try {
      final WebElement answerElement = browserService.getWebDriver().findElement(By.id("otppswd"));
      final String token = tokenService.get("Please enter token for Voya 2-factor auth");

      answerElement.sendKeys(token);

      browserService.getWebDriver().findElement(By.id("submit-button")).click();

      // register this device
      browserService
          .getWebDriver()
          .findElement(
              By.xpath(
                  "//*[@id=\"doc-main-inner\"]/compose/main/form/div[2]/voya-field/div/label/span[2]/b"))
          .click();

      // continue
      browserService
          .getWebDriver()
          .findElement(
              By.xpath("//*[@id=\"doc-main-inner\"]/compose/main/form/div[4]/voya-button/span"))
          .click();

    } catch (NoSuchElementException e) {
      doNoTokenRequired();
    }
  }

  protected void doNoTokenRequired() {}

  public void updateAccountBalances() {
    try {
      WebElement webElement =
          browserService
              .getWebDriver()
              .findElement(
                  By.xpath(
                      "//*[@id=\"main-content\"]/div[1]/div[2]/div[2]/div/div[2]/div[1]/div[2]/div/div[2]/div/div[2]/table/tbody/tr[1]/td[1]/span/a/span/span[1]"));
      handleElement(webElement);
    } catch (NoSuchElementException e) {
      WebDriverUtil.logException(webSession, e);
      throw e;
    }
  }

  protected void handleElement(WebElement webElement) {}

  public void logout() {
    browserService
        .getWebDriver()
        .findElement(
            By.xpath(
                "//*[@id=\"doc-header\"]/header/voya-header/div/div[1]/voya-top-nav/div/div/voya-main-nav/div/main-nav-base-menu/div/base-menu-item[5]/a"))
        .click();
  }

  @Override
  public BrowserService getBrowserService() {
    return browserService;
  }
}
