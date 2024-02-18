package com.walterjwhite.browser.api.authentication.field;

import lombok.Data;
import lombok.ToString;

import javax.jdo.annotations.PersistenceCapable;

@ToString(doNotUseGetters = true)
@Data
@PersistenceCapable
public class FieldGroup {
  protected FieldPair[] fieldPairs;
  

  protected Locator submitLocator;
}
