package com.walterjwhite.browser.api.authentication;

import com.walterjwhite.browser.api.authentication.field.AbstractFieldEntry;
import com.walterjwhite.browser.api.authentication.field.FieldPair;
import com.walterjwhite.browser.api.model.WebSession;
import org.openqa.selenium.WebElement;

// trigger)
// aka. Captcha
public class ManualIntervention extends AbstractFieldEntry {
  @Override
  public WebElement enter(WebSession webSession, FieldPair fieldPair) {
    // prompt the user to enter
    // then wait till confirmed
    // then proceed

    return null;
  }
}
