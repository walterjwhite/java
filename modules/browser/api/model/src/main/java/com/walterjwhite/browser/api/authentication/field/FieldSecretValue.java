package com.walterjwhite.browser.api.authentication.field;

import com.walterjwhite.property.api.property.Secret;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
@PersistenceCapable
public class FieldSecretValue implements FieldSecret {
  protected Secret secret;
}
