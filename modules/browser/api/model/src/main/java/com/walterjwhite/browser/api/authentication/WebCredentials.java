package com.walterjwhite.browser.api.authentication;

import com.walterjwhite.browser.api.authentication.field.AbstractFieldSecret;
import com.walterjwhite.browser.api.model.Website;
import lombok.Data;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
public class WebCredentials {
  protected Website website;
  protected AbstractFieldSecret[] fieldSecrets;
}
