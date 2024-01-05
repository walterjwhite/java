package com.walterjwhite.browser.api.util;

import com.walterjwhite.browser.api.model.BrowserActionInstance;
import java.util.Map;

public interface ArgumentReplacer {
  static int replace(
      final BrowserActionInstance[] browserActionInstances, final Map<String, String> values) {
    int replaced = 0;
    for (final BrowserActionInstance browserActionInstance : browserActionInstances) {
      if (browserActionInstance.getArguments() != null) {
        replaced += doReplace(browserActionInstance, values);
      }
    }

    return replaced;
  }

  static int doReplace(
      final BrowserActionInstance browserActionInstance, final Map<String, String> values) {
    int replaced = 0;
    for (int i = 0; i < browserActionInstance.getArguments().length; i++) {
      for (final Map.Entry<String, String> keyValue : values.entrySet()) {
        if (keyValue.getKey().equals(browserActionInstance.getArguments()[i])) {
          browserActionInstance.getArguments()[i] = keyValue.getValue();
          replaced++;
        }
      }
    }

    return replaced;
  }

  //  static void put(final Map<String, String> templateMap, final String keyName, final String
  // value) {
  //    templateMap.put("<" + keyName + ">", value);
  //  }
}
