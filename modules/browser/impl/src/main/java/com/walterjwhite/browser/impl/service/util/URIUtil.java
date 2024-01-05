package com.walterjwhite.browser.impl.service.util;

public class URIUtil {

  public static String removeProtocol(final String resourceURI) {
    int index = resourceURI.toLowerCase().indexOf("://");
    if (index > 0) {
      return (resourceURI.substring(index + 3));
    }

    return (resourceURI);
  }

  public static String filterURIForFileStorage(final String resourceURI) {
    return (URIUtil.removeProtocol(resourceURI).replaceAll("[\\s\\\\]+", ""));
  }
}
