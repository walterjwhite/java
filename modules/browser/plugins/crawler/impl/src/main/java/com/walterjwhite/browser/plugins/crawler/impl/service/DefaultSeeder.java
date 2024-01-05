package com.walterjwhite.browser.plugins.crawler.impl.service;

import com.walterjwhite.browser.plugins.crawler.api.model.CrawlSession;
import com.walterjwhite.browser.plugins.crawler.api.service.Seeder;
import com.walterjwhite.browser.plugins.crawler.impl.FindNextCrawlQuery;
import com.walterjwhite.datastore.api.repository.Repository;
import jakarta.inject.Inject;
import javax.transaction.Transactional;

public class DefaultSeeder implements Seeder {

  protected final Repository repository;

  @Inject
  public DefaultSeeder(Repository repository) {
    this.repository = repository;
  }

  @Override
  @Transactional
  public CrawlSession get() {
    return repository.create(new CrawlSession(repository.query(new FindNextCrawlQuery())));
  }
}
