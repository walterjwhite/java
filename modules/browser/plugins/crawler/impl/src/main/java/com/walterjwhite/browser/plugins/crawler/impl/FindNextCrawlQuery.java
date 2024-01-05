package com.walterjwhite.browser.plugins.crawler.impl;

import com.walterjwhite.browser.plugins.crawler.api.model.Crawl;
import com.walterjwhite.datastore.query.AbstractQuery;
import lombok.Getter;

@Getter
public class FindNextCrawlQuery extends AbstractQuery<Crawl, Crawl> {

  public FindNextCrawlQuery() {
    super(0, 2, Crawl.class, Crawl.class, false);
  }
}
