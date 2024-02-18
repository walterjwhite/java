package com.walterjwhite.browser.api.authentication;

import com.walterjwhite.browser.api.authentication.field.FieldSecret;
import com.walterjwhite.browser.api.model.Website;
import lombok.Data;
import lombok.ToString;

import javax.jdo.annotations.PersistenceCapable;

@ToString(doNotUseGetters = true)
@Data
@PersistenceCapable
public class WebCredentials {
  protected Website website;
  protected FieldSecret[] fieldSecrets;
}
