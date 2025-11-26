package com.walterjwhite.browser.api.authentication.password;

import com.walterjwhite.browser.api.authentication.AuthenticatedWebSession;
import com.walterjwhite.browser.api.model.BrowserActionInstance;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class NewPasswordWebSession extends AuthenticatedWebSession {
  protected BrowserActionInstance[] browserActionInstances;
}
