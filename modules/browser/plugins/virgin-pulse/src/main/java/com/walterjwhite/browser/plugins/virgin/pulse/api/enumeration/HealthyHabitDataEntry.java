package com.walterjwhite.browser.plugins.virgin.pulse.api.enumeration;

import com.walterjwhite.browser.api.enumeration.LocatorType;
import com.walterjwhite.browser.api.util.WebDriverUtil;
import com.walterjwhite.browser.plugins.virgin.pulse.VirginPulseAction;
import com.walterjwhite.browser.plugins.virgin.pulse.api.ElementAction;
import com.walterjwhite.browser.plugins.virgin.pulse.api.model.VirginPulseWebSession;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

@Slf4j
public enum HealthyHabitDataEntry implements VirginPulseAction {
  Mood {
    public void execute(final VirginPulseWebSession virginPulseWebSession, final String argument)
        throws Exception {
      final String xPath =
          "//*[@id=\"tracker_10\"]/mood-widget/div/div[1]/div/form/div/div[2]/div/div[2]/div[5]/div/img";
      final WebElement webElement =
          WebDriverUtil.waitUntilFound(
              virginPulseWebSession.getWebDriver(),
              By.xpath(xPath),
              virginPulseWebSession.getElementLocatorWaitDuration());

      if (HealthyHabitToggle.wasAlreadyPerformed(webElement)) {
        LOGGER.warn("already performed");
      } else {
        ElementAction.Click.execute(virginPulseWebSession, LocatorType.XPath, xPath);
      }
    }
  },
  Weight {
    public void execute(final VirginPulseWebSession virginPulseWebSession, final String argument)
        throws Exception {
      ElementAction.Type.execute(
          virginPulseWebSession, LocatorType.Id, "healthyhabits-weightinput", argument);
      ElementAction.Click.execute(virginPulseWebSession, LocatorType.Id, "track-weight", argument);
    }
  },
  Sleep {
    public void execute(final VirginPulseWebSession virginPulseWebSession, final String argument)
        throws Exception {
      final String[] arguments = split(argument);

      ElementAction.Type.execute(virginPulseWebSession, LocatorType.Id, "sleepHours", arguments[0]);
      if (arguments.length == 2) {
        ElementAction.Type.execute(
            virginPulseWebSession, LocatorType.Id, "sleepMinutes", arguments[1]);
      }

      ElementAction.Click.execute(virginPulseWebSession, LocatorType.Id, "track-sleep", argument);
    }
  },
  Steps {
    public void execute(final VirginPulseWebSession virginPulseWebSession, final String argument)
        throws Exception {
      ElementAction.Type.execute(virginPulseWebSession, LocatorType.Id, "numberOfSteps", argument);
      ElementAction.Click.execute(virginPulseWebSession, LocatorType.Id, "track-steps", argument);
    }
  },
  Workout {
    public void execute(final VirginPulseWebSession virginPulseWebSession, final String argument)
        throws Exception {
      final String[] arguments = split(argument);

      ElementAction.Type.execute(
          virginPulseWebSession, LocatorType.Id, "steps-converter-activity-input", arguments[0]);
      // these actions don't seem to do anything?
      Actions actions = new Actions(virginPulseWebSession.getWebDriver());
      actions.sendKeys(Keys.TAB).build().perform();
      actions.sendKeys(Keys.RETURN).build().perform();

      ElementAction.Type.execute(
          virginPulseWebSession, LocatorType.Id, "steps-converter-time-input", arguments[1]);
      ElementAction.Type.execute(
          virginPulseWebSession, LocatorType.Id, "steps-converter-distance-input", arguments[2]);

      ElementAction.Click.execute(virginPulseWebSession, LocatorType.Id, "steps-converter-submit");

      //      webDriver
      //          .findElement(
      //              By.xpath(
      //
      // "/html/body/div[2]/div/div/div[1]/healthy-habits-component/div/div[2]/div[2]/div/div[1]/div[2]/div/div[11]/duration-activity-widget/div/div[1]/div[1]/steps-converter-input/div/div[2]/button/span"))
      //          .click();
      //      actions.sendKeys(Keys.TAB).build().perform();
      //      actions.sendKeys(Keys.RETURN).build().perform();
    }
  };

  private static String[] split(final String arguments) {
    if (arguments == null || arguments.length() == 0) {
      return null;
    }

    return arguments.split("\\|");
  }
}
