package com.walterjwhite.browser.api.authentication.field;

import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
@PersistenceCapable
public class FieldGroup {
  protected FieldPair[] fieldPairs;
  protected Locator submitLocator;
}
