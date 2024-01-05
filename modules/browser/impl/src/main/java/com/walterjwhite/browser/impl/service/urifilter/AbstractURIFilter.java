package com.walterjwhite.browser.impl.service.urifilter;

import com.walterjwhite.browser.api.service.URIFilter;
import com.walterjwhite.browser.impl.service.util.URIUtil;

public abstract class AbstractURIFilter implements URIFilter {
  @Override
  public boolean matches(String uri) {
    return (doMatch(URIUtil.removeProtocol(uri)));
  }

  protected abstract boolean doMatch(final String uri);
}
