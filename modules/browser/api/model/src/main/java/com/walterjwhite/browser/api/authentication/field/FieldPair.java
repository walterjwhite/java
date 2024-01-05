package com.walterjwhite.browser.api.authentication.field;

import com.walterjwhite.browser.api.authentication.FieldProcessor;
import com.walterjwhite.browser.api.authentication.FieldSecretType;
import lombok.Data;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
public class FieldPair {
  protected String id;

  protected FieldSecretType fieldSecretType;
  protected Locator inputLocator;
  protected Locator labelLocator;

  // if elements are not found, should the login process fail?
  protected boolean required = true;

  protected Class<? extends FieldProcessor> fieldProcessorClass;
}
