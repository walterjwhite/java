package com.walterjwhite.browser.api.authentication.field;

import lombok.Data;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
public class FieldGroup {
  protected FieldPair[] fieldPairs;
  // different actions taken on them
  protected Locator submitLocator;
}
