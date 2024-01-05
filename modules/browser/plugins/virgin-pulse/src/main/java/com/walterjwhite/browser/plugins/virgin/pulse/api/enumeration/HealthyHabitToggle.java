package com.walterjwhite.browser.plugins.virgin.pulse.api.enumeration;

import com.walterjwhite.browser.api.enumeration.LocatorType;
import com.walterjwhite.browser.api.enumeration.WebElementBrowserAction;
import com.walterjwhite.browser.api.util.WebDriverUtil;
import com.walterjwhite.browser.plugins.virgin.pulse.VirginPulseAction;
import com.walterjwhite.browser.plugins.virgin.pulse.api.model.VirginPulseWebSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Slf4j
@Getter
@RequiredArgsConstructor
public enum HealthyHabitToggle implements VirginPulseAction {
  Fiber(625),
  Fruit(633),
  Water(652),
  Stairs(13),

  Bed(641),
  Breakfast(44),
  H2ODuo(6068, false),
  SteadySips(6067),
  Unsweetened(630),
  DeviceFreeZone(644),
  Protein(624),
  ExerciseBreak(687),
  Vegetables(635);

  private final int id;
  private final boolean completed;

  HealthyHabitToggle(int id) {
    this(id, true);
  }

  public void execute(final VirginPulseWebSession virginPulseWebSession, final String argument)
      throws Exception {
    final WebElement element = getElement(virginPulseWebSession);

    if (!wasAlreadyPerformed(element)) {
      WebElementBrowserAction.MoveTo.execute(
          virginPulseWebSession.getWebDriver(),
          virginPulseWebSession,
          LocatorType.Id,
          element.getAttribute("id"));

      WebElementBrowserAction.Click.execute(
          virginPulseWebSession.getWebDriver(),
          virginPulseWebSession,
          LocatorType.Id,
          element.getAttribute("id"));
    }
  }

  protected WebElement getElement(final VirginPulseWebSession virginPulseWebSession) {

    try {
      return WebDriverUtil.waitUntilFound(
          virginPulseWebSession.getWebDriver(),
          By.id(String.format("tracker-%d-track-yes", id)),
          virginPulseWebSession.getElementLocatorWaitDuration());
    } catch (Exception e) {
      WebDriverUtil.logException(virginPulseWebSession, e);
      throw e;
    }
  }

  public static boolean wasAlreadyPerformed(final WebElement webElement) {
    return !webElement.getAttribute("class").contains("inverse");
  }
}
