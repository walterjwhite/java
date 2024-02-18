package com.walterjwhite.browser.api.authentication.password;

import com.walterjwhite.browser.api.authentication.AuthenticatedWebSession;
import com.walterjwhite.browser.api.model.BrowserActionInstance;
import lombok.Data;
import lombok.ToString;

import javax.jdo.annotations.PersistenceCapable;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class NewPasswordWebSession extends AuthenticatedWebSession {

  protected BrowserActionInstance[] browserActionInstances;
}
