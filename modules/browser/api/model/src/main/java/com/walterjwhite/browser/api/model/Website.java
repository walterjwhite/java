package com.walterjwhite.browser.api.model;

import com.walterjwhite.browser.api.authentication.field.FieldGroup;
import com.walterjwhite.browser.api.authentication.field.Locator;
import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import com.walterjwhite.identity.api.model.password.PasswordPolicy;
import java.time.Duration;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
@Data
public class Website extends AbstractNamedEntity {
  protected String uri;

  @EqualsAndHashCode.Exclude protected Duration sessionTimeout;
  @EqualsAndHashCode.Exclude protected BrowserActionInstance[] preAuthenticationActions;
  @EqualsAndHashCode.Exclude protected BrowserActionInstance[] postAuthenticationActions;

  @EqualsAndHashCode.Exclude protected PasswordPolicy passwordPolicy;

  @EqualsAndHashCode.Exclude protected BrowserActionInstance[] passwordChangeScript;

  @EqualsAndHashCode.Exclude protected FieldGroup[] fieldGroups;

  @EqualsAndHashCode.Exclude protected Locator loggedInLocator;

  @EqualsAndHashCode.Exclude protected BrowserActionInstance[] logoutActions;

  @EqualsAndHashCode.Exclude protected Feature[] features;

  @Override
  public String getId() {
    return uri;
  }
}
