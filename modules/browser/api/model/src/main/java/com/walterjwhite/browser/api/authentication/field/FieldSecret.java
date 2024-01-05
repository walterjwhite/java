package com.walterjwhite.browser.api.authentication.field;

import com.walterjwhite.infrastructure.inject.core.Secret;
import lombok.Data;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
public class FieldSecret extends AbstractFieldSecret {
  protected Secret secret;
}
