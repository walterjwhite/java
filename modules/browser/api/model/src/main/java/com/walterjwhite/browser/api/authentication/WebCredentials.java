package com.walterjwhite.browser.api.authentication;

import com.walterjwhite.browser.api.authentication.field.FieldSecret;
import com.walterjwhite.browser.api.model.Website;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
@PersistenceCapable
public class WebCredentials {
  protected Website website;
  protected FieldSecret[] fieldSecrets;
}
