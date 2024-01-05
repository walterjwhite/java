package com.walterjwhite.browser.plugins.crawler.api.model;

import com.walterjwhite.browser.api.model.BrowserSessionResourceURI;
import com.walterjwhite.browser.api.model.ResourceURI;
import com.walterjwhite.browser.plugins.crawler.api.enumeration.CrawlJobStatus;
import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
@PersistenceCapable
public class CrawlSessionResourceURI extends AbstractEntity {

  protected CrawlSession crawlSession;

  protected ResourceURI resourceURI;

  @EqualsAndHashCode.Exclude protected int depth;

  //  protected boolean crawled;
  @EqualsAndHashCode.Exclude protected CrawlJobStatus crawlJobStatus;

  @EqualsAndHashCode.Exclude protected BrowserSessionResourceURI fromBrowserSessionResourceURI;

  /**
   * NOTE: this field will be empty until it is crawled, perhaps we use this as the criteria to
   * determine what needs crawled
   */
  @EqualsAndHashCode.Exclude protected BrowserSessionResourceURI browserSessionResourceURI;

  public CrawlSessionResourceURI(
      CrawlSession crawlSession,
      ResourceURI resourceURI,
      int depth,
      BrowserSessionResourceURI fromBrowserSessionResourceURI) {

    this.crawlSession = crawlSession;
    this.resourceURI = resourceURI;
    this.depth = depth;
    this.fromBrowserSessionResourceURI = fromBrowserSessionResourceURI;
    //    this.crawled = false;
    crawlJobStatus = CrawlJobStatus.New;
  }

  public CrawlSessionResourceURI(CrawlSession crawlSession, ResourceURI resourceURI, int depth) {

    this.crawlSession = crawlSession;
    this.resourceURI = resourceURI;
    this.depth = depth;
    //    this.crawled = false;
    crawlJobStatus = CrawlJobStatus.New;
  }
}
