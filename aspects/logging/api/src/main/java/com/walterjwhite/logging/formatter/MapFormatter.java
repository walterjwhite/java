package com.walterjwhite.logging.formatter;

import com.walterjwhite.logging.util.ArgumentUtil;
import java.util.Iterator;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MapFormatter {
  public static Object format(final boolean isSensitive, Map argument, final int argumentLength) {
    final String[] out = new String[argumentLength];
    final Iterator<Map.Entry> iterator = argument.entrySet().iterator();

    for (int i = 0; i < argumentLength; i++) {
      out[i] = ArgumentUtil.format(iterator.next(), isSensitive);
    }

    return out;
  }
}
