package com.walterjwhite.browser.plugins.pnc.service;

import com.walterjwhite.authentication.api.model.command.ChangeUsernameCommand;
import org.openqa.selenium.By;

public class ChangePNCUsernamePlugin extends AbstractPNCPlugin {
  protected ChangeUsernameCommand changeUsernameCommand;

  @Override
  public void execute() {
    browserService.getWebDriver().switchTo().frame(0);

    browserService.getWebDriver().findElement(By.xpath("//*[@id=\"secondTab\"]/a")).click();

    browserService.getWebDriver().findElement(By.id("userIdEdit")).click();

    browserService
        .getWebDriver()
        .findElement(By.id("userId"))
        .sendKeys(changeUsernameCommand.getNewUsername());

    browserService
        .getWebDriver()
        .findElement(By.id("currentPasswordLbl"))
        .sendKeys((changeUsernameCommand.getAccount().getClientId().getPlainText()));

    browserService
        .getWebDriver()
        .findElement(By.xpath("//*[@id=\"contentArea\"]/div[3]/div[2]/div[1]/div/input"))
        .click();
  }
}
// *[@id="leftCol_half"]/div/div[2]/table/tbody/tr[4]/th/div/a
