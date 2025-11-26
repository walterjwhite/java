package com.walterjwhite.browser.api.service;

import com.walterjwhite.browser.api.authentication.field.FieldPair;
import com.walterjwhite.browser.api.model.WebSession;
import org.openqa.selenium.WebElement;

public interface FieldEntryService {
  WebElement enter(final WebSession webSession, final FieldPair fieldPair)
      throws InterruptedException;
}
