package com.walterjwhite.browser.plugins.crawler.api.service;

import com.walterjwhite.browser.api.model.BrowserSessionResourceURI;
import java.util.Set;

/** Manages the URI queue */
public interface ResourceURIQueue {
  boolean add(BrowserSessionResourceURI sessionResourceURI);

  boolean addAll(Set<BrowserSessionResourceURI> sessionResourceURIs);
}
