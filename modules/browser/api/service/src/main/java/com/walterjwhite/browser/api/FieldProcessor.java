package com.walterjwhite.browser.api;

import com.walterjwhite.browser.api.authentication.field.FieldPair;
import com.walterjwhite.browser.api.authentication.field.FieldSecretValue;

public interface FieldProcessor {
  String get(final FieldPair fieldPair, final FieldSecretValue fieldSecretValue);
}
