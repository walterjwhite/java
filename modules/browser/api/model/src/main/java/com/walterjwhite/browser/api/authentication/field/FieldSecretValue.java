package com.walterjwhite.browser.api.authentication.field;

import com.walterjwhite.property.api.property.Secret;
import lombok.Data;
import lombok.ToString;

import javax.jdo.annotations.PersistenceCapable;

@ToString(doNotUseGetters = true)
@Data
@PersistenceCapable
public class FieldSecretValue implements FieldSecret {
  protected Secret secret;
}
