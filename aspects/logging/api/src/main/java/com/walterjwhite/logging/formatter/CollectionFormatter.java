package com.walterjwhite.logging.formatter;

import com.walterjwhite.logging.util.ArgumentUtil;
import java.util.Collection;
import java.util.Iterator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CollectionFormatter {
  public static Object format(
      final boolean isSensitive, Collection argument, final int argumentLength) {
    final String[] out = new String[argumentLength];
    final Iterator<Object> iterator = argument.iterator();

    for (int i = 0; i < argumentLength; i++) {
      out[i] = ArgumentUtil.format(iterator.next(), isSensitive);
    }

    return out;
  }
}
