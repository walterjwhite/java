package com.walterjwhite.browser.plugins.pnc.service;

import com.walterjwhite.authentication.api.model.command.ChangePasswordCommand;
import org.openqa.selenium.By;

public class ChangePNCPasswordPlugin extends AbstractPNCPlugin {
  protected ChangePasswordCommand changePasswordCommand;

  @Override
  public void execute() {
    browserService.getWebDriver().switchTo().frame(0);

    browserService.getWebDriver().findElement(By.xpath("//*[@id=\"secondTab\"]/a")).click();

    browserService.getWebDriver().findElement(By.id("pwdEdit")).click();

    browserService
        .getWebDriver()
        .findElement(By.id("password"))
        .sendKeys(changePasswordCommand.getNewPassword());

    browserService
        .getWebDriver()
        .findElement(By.id("confirmPassword"))
        .sendKeys(changePasswordCommand.getNewPassword());

    browserService
        .getWebDriver()
        .findElement(By.id("currentPasswordLbl"))
        .sendKeys(
            (changePasswordCommand
                .getAccount()
                .getChallengeResponses()
                .get(0)
                .getResponse()
                .getPlainText()));

    browserService
        .getWebDriver()
        .findElement(By.xpath("//*[@id=\"contentArea\"]/div[3]/div[2]/div[1]/div/input"))
        .click();
  }
}
