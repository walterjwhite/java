package com.walterjwhite.browser.plugins.crawler.api.service;

import com.walterjwhite.browser.plugins.crawler.api.model.CrawlSession;

public interface CrawlerService {
  int getDepth();

  CrawlSession getCrawlSession();

  void visit() throws Exception;
}
