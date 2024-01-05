package com.walterjwhite.browser.plugins.crawler.impl;

import com.walterjwhite.browser.plugins.crawler.api.model.CrawlSession;
import com.walterjwhite.browser.plugins.crawler.api.model.CrawlSessionResourceURI;
import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.queue.api.job.AbstractRunnable;
import com.walterjwhite.queue.api.service.QueueService;
import com.walterjwhite.queue.event.annotation.SubscribeTo;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import javax.transaction.Transactional;
import org.openqa.selenium.WebDriver;

@SubscribeTo(eventClass = CrawlSessionResourceURI.class)
public class CrawlerRunnable extends AbstractRunnable {
  protected final WebDriver webDriver;
  protected final Provider<Repository> repositoryProvider;

  @Inject
  public CrawlerRunnable(
      CrawlSession crawlSession,
      WebDriver webDriver,
      Provider<Repository> repositoryProvider,
      QueueService queueService) {

    this.webDriver = webDriver;
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  protected void doCall() throws Exception {
    visit();
  }

  @Transactional
  public void visit() {
    // NOTE: all of the downstream services are handled automatically by observing the
    // @AfterNavigateTo event
    // browserService.get(queued..getResourceURI());

    // entity.setBrowserSessionResourceURI(browserService.getCurrentResourceURI());
    // repositoryProvider.get().merge(entity);
  }
}
