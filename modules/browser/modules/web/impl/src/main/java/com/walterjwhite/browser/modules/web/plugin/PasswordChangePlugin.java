package com.walterjwhite.browser.modules.web.plugin;

import com.walterjwhite.browser.api.authentication.FieldSecretType;
import com.walterjwhite.browser.api.authentication.WebsiteAuthenticator;
import com.walterjwhite.browser.api.authentication.field.FieldGroup;
import com.walterjwhite.browser.api.authentication.field.FieldPair;
import com.walterjwhite.browser.api.authentication.field.FieldSecret;
import com.walterjwhite.browser.api.authentication.password.NewPasswordWebSession;
import com.walterjwhite.browser.api.plugin.AbstractWebsitePlugin;
import com.walterjwhite.browser.api.util.ArgumentReplacer;
import com.walterjwhite.browser.api.util.BrowserActionInstanceUtil;
import com.walterjwhite.identity.api.service.PasswordGenerator;
import com.walterjwhite.property.api.SecretService;
import java.util.Arrays;
import java.util.Map;
import jakarta.inject.Inject;

public class PasswordChangePlugin extends AbstractWebsitePlugin<NewPasswordWebSession> {
  protected final SecretService secretService;

  @Inject
  public PasswordChangePlugin(
      WebsiteAuthenticator websiteAuthenticator, final SecretService secretService) {
    super(websiteAuthenticator);

    this.secretService = secretService;
  }

  @Override
  protected void doExecute(final NewPasswordWebSession webSession) throws Exception {
    final String newPassword = generateNewPassword(webSession);

    replaceNewPassword(webSession, newPassword);
    doUpdatePassword(webSession);

    updatePasswordSecret(webSession, newPassword);
  }

  protected String generateNewPassword(final NewPasswordWebSession webSession) {
    return PasswordGenerator.generate(
        webSession.getWebCredentials().getWebsite().getPasswordPolicy());
  }

  protected void replaceNewPassword(
      final NewPasswordWebSession webSession, final String newPassword) {
    if (ArgumentReplacer.replace(
            webSession.getWebCredentials().getWebsite().getPasswordChangeScript(),
            Map.of("NEW_PASSWORD", newPassword))
        < 1) {
      throw new IllegalStateException("Expected to change NEW_PASSWORD at least once");
    }
  }

  protected void doUpdatePassword(final NewPasswordWebSession webSession)
      throws InterruptedException {
    BrowserActionInstanceUtil.execute(
        webSession,
        webSession.getWebDriver(),
        webSession.getWebCredentials().getWebsite().getPasswordChangeScript());
  }

  protected void updatePasswordSecret(
      final NewPasswordWebSession webSession, final String newPassword) {
    secretService.put(
        getPasswordSecretKey(webSession), "update via " + getClass().getName(), newPassword);
  }

  public static String getPasswordSecretKey(final NewPasswordWebSession webSession) {
    final String fieldId = getPasswordFieldPairFieldId(webSession);

    return Arrays.stream(webSession.getWebCredentials().getFieldSecrets())
        .filter(
            fieldSecret ->
                fieldSecret instanceof FieldSecret && fieldSecret.getFieldPairId().equals(fieldId))
        .findFirst()
        .orElseThrow()
        .getSecret()
        .getKey();
  }

  public static String getPasswordFieldPairFieldId(final NewPasswordWebSession webSession) {
    for (final FieldGroup fieldGroup :
        webSession.getWebCredentials().getWebsite().getFieldGroups()) {
      for (final FieldPair fieldPair : fieldGroup.getFieldPairs()) {
        if (FieldSecretType.Password.equals(fieldPair.getFieldSecretType())) {
          return fieldPair.getId();
        }
      }
    }

    throw new IllegalStateException("Password field not found.");
  }
}
