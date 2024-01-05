package com.walterjwhite.browser.api.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeleteElementBuilder {
  /** Wraps query to actually perform the delete. */
  public static String build(final String query) {
    final StringBuilder buffer = new StringBuilder();

    buffer.append("v = ");
    buffer.append(query);
    buffer.append("if(v != null && v.length > 0){v[0].remove();}\n");

    return buffer.toString();
  }
}
