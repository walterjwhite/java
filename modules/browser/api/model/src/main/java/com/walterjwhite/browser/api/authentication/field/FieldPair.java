package com.walterjwhite.browser.api.authentication.field;

import com.walterjwhite.browser.api.authentication.FieldSecretType;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
@PersistenceCapable
public class FieldPair {
  protected String id;

  protected FieldSecretType fieldSecretType;
  protected Locator inputLocator;
  protected Locator labelLocator;

  protected boolean required = true;

}
