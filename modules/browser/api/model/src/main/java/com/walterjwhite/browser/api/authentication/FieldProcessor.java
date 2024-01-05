package com.walterjwhite.browser.api.authentication;

import com.walterjwhite.browser.api.authentication.field.FieldPair;
import com.walterjwhite.browser.api.authentication.field.FieldSecret;

public interface FieldProcessor {
  String get(final FieldPair fieldPair, final FieldSecret fieldSecret);
}
