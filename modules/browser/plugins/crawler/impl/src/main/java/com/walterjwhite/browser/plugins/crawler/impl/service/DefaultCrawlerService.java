package com.walterjwhite.browser.plugins.crawler.impl.service;

import com.walterjwhite.browser.api.service.BrowserService;
import com.walterjwhite.browser.plugins.crawler.api.model.CrawlSession;
import com.walterjwhite.browser.plugins.crawler.api.model.CrawlSessionResourceURI;
import com.walterjwhite.browser.plugins.crawler.api.service.CrawlerService;
import com.walterjwhite.datastore.api.repository.Repository;
import jakarta.inject.Inject;
import jakarta.inject.Provider;

public class DefaultCrawlerService implements CrawlerService {
  protected final Provider<Repository> repositoryProvider;
  protected CrawlSessionResourceURI currentResourceURI;

  @Inject
  public DefaultCrawlerService(
      BrowserService browserService, Provider<Repository> repositoryProvider) {
    this.browserService = browserService;
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public int getDepth() {
    if (currentResourceURI == null) return (0);
    return currentResourceURI.getDepth();
  }

  @Override
  public CrawlSession getCrawlSession() {
    return currentResourceURI.getCrawlSession();
  }

  @Override
  protected void finalize() throws Throwable {
    browserService.close();
    super.finalize();
  }
}
