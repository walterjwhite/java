package com.walterjwhite.browser.api.authentication.password;

import com.walterjwhite.browser.api.authentication.AuthenticatedWebSession;
import com.walterjwhite.browser.api.model.BrowserActionInstance;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class NewPasswordWebSession extends AuthenticatedWebSession {
  // actions required to update the password
  protected BrowserActionInstance[] browserActionInstances;
}
