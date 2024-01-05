package com.walterjwhite.browser.api.util;

import com.walterjwhite.browser.api.authentication.WebCredentials;
import com.walterjwhite.browser.api.authentication.field.AbstractFieldSecret;
import com.walterjwhite.browser.api.authentication.field.FieldGroup;
import com.walterjwhite.browser.api.authentication.field.FieldPair;
import com.walterjwhite.browser.api.model.Website;
import java.util.Arrays;

public interface FieldPairUtil {
  static FieldPair get(final Website website, final String fieldId) {
    for (final FieldGroup fieldGroup : website.getFieldGroups()) {
      for (final FieldPair fieldPair : fieldGroup.getFieldPairs()) {
        if (fieldPair.getId().equals(fieldId)) {
          return fieldPair;
        }
      }
    }

    throw new IllegalArgumentException(fieldId + " was not mapped.");
  }

  static AbstractFieldSecret get(final FieldPair fieldPair, final WebCredentials webCredentials) {
    return Arrays.stream(webCredentials.getFieldSecrets())
        .filter(fieldSecret -> fieldSecret.getFieldPairId().equals(fieldPair.getId()))
        .findFirst()
        .orElseThrow();
  }
}
