package com.walterjwhite.browser.api.service;

import com.walterjwhite.browser.api.authentication.field.FieldPair;
import com.walterjwhite.browser.api.model.WebSession;
import com.walterjwhite.property.api.SecretService;
import com.walterjwhite.property.api.property.Secret;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebElement;

@RequiredArgsConstructor
public class FieldSecretEntryService implements FieldEntryService {
  private final SecretService secretService;

  @Override
  public WebElement enter(final WebSession webSession, final FieldPair fieldPair)
      throws InterruptedException {


    final Secret secret = null; /*fieldSecret.getSecret();*/
    secret.setValue(secretService.get(secret.getKey()));



    return null;
  }
}
