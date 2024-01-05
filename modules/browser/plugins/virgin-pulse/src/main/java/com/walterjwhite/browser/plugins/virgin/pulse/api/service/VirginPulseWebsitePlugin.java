package com.walterjwhite.browser.plugins.virgin.pulse.api.service;

import com.walterjwhite.browser.api.authentication.WebsiteAuthenticator;
import com.walterjwhite.browser.api.enumeration.LocatorType;
import com.walterjwhite.browser.api.enumeration.WebElementBrowserAction;
import com.walterjwhite.browser.api.plugin.AbstractWebsitePlugin;
import com.walterjwhite.browser.plugins.virgin.pulse.api.enumeration.HealthyHabitDataEntry;
import com.walterjwhite.browser.plugins.virgin.pulse.api.enumeration.HealthyHabitToggle;
import com.walterjwhite.browser.plugins.virgin.pulse.api.model.VirginPulseWebSession;
import java.time.LocalDate;
import java.util.Map;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

@Slf4j
public class VirginPulseWebsitePlugin extends AbstractWebsitePlugin<VirginPulseWebSession> {
  @Inject
  public VirginPulseWebsitePlugin(WebsiteAuthenticator websiteAuthenticator) {
    super(websiteAuthenticator);
  }

  @Override
  protected void doExecute(VirginPulseWebSession virginPulseWebSession) throws Exception {
    virginPulseWebSession.getWebDriver().get("https://app.member.virginpulse.com/#/healthyhabits");

    // limit this
    do {
      if (virginPulseWebSession.getHealthyHabitDataEntries() != null) {
        for (Map.Entry<HealthyHabitDataEntry, String> healthyHabitEntry :
            virginPulseWebSession.getHealthyHabitDataEntries().entrySet()) {
          try {
            healthyHabitEntry.getKey().execute(virginPulseWebSession, healthyHabitEntry.getValue());
          } catch (Exception e) {
            LOGGER.warn(
                String.format(
                    "error executing: %s => %s",
                    healthyHabitEntry.getKey().name(), healthyHabitEntry.getValue()),
                e);
          }
        }
      }

      if (virginPulseWebSession.getHealthyHabitDataEntries() != null) {
        for (HealthyHabitToggle healthyHabitToggle :
            virginPulseWebSession.getHealthyHabitToggles()) {
          try {
            healthyHabitToggle.execute(virginPulseWebSession, null);
          } catch (Exception e) {
            LOGGER.warn(String.format("error executing: %s", healthyHabitToggle), e);
          }
        }
      }

      if (virginPulseWebSession.isTodayOnly()) {
        break;
      }

      getDate(virginPulseWebSession);
      navigateToPreviousDay(virginPulseWebSession);
    } while (true);
  }

  protected void navigateToPreviousDay(final VirginPulseWebSession virginPulseWebSession)
      throws InterruptedException {
    WebElementBrowserAction.Click.execute(
        virginPulseWebSession.getWebDriver(),
        virginPulseWebSession,
        LocatorType.Id,
        "healthyhabits-datearrowback");
    //    WebDriverUtil.click(
    //        virginPulseWebSession.getBrowserActionDelayableMap().get(BrowserAction.Click),
    //        virginPulseWebSession
    //            .getWebDriver()
    //            .findElement(By.xpath("//*[@id=\"healthyhabits-datearrowback\"]")));
  }

  protected LocalDate getDate(VirginPulseWebSession virginPulseWebSession) {
    final String dateString =
        virginPulseWebSession
            .getWebDriver()
            .findElement(
                By.xpath(
                    "/html/body/div[2]/div/div/div[1]/healthy-habits-component/div/div[2]/div[2]/div/div[1]/tracker-date-picker/div/div/div/div[1]/div[2]"))
            .getText();

    final String monthDay = dateString.replaceFirst("[a-zA-Z]{3,}(day|DAY) ", "");
    final String[] dateParts = monthDay.split("-");

    return LocalDate.of(
        LocalDate.now().getYear(), Integer.valueOf(dateParts[0]), Integer.valueOf(dateParts[1]));
  }
}
