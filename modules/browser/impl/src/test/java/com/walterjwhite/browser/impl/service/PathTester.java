package com.walterjwhite.browser.impl.service;

import com.walterjwhite.browser.impl.service.util.URIUtil;

public class PathTester {

  public static void main(final String[] arguments) {
    final String input = "http://www.google.com";

    // LOGGER.debug("output:" + URIUtil.removeProtocol(input).replaceAll("[\\W]+", ""));
    LOGGER.debug("output:" + URIUtil.removeProtocol(input).replaceAll("[\\s\\\\]+", ""));
  }
}
