package com.walterjwhite.browser.plugins.crawler.api.query;

import com.walterjwhite.browser.plugins.crawler.api.model.CrawlSession;
import com.walterjwhite.browser.plugins.crawler.api.model.CrawlSessionResourceURI;
import com.walterjwhite.datastore.query.AbstractQuery;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class FindCrawlSessionResourceByCrawlSession
    extends AbstractQuery<CrawlSessionResourceURI, CrawlSessionResourceURI> {
  protected final CrawlSession crawlSession;

  public FindCrawlSessionResourceByCrawlSession(
      int offset, int recordCount, CrawlSession crawlSession) {
    super(offset, recordCount, CrawlSessionResourceURI.class, CrawlSessionResourceURI.class, false);
    this.crawlSession = crawlSession;
  }
}
