package com.walterjwhite.browser.plugins.crawler.api.model;

import com.walterjwhite.browser.api.model.ResourceURI;
import com.walterjwhite.browser.plugins.crawler.api.enumeration.CrawlFrequency;
import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@PersistenceCapable
public class Crawl extends AbstractNamedEntity {
  /** IE. lxer.com, techbargains.com, pittsurplus.com, etc. */
  protected Set<ResourceURI> seedResourceURIs;

  protected Set<CrawlSession> crawlSessions;
  protected CrawlFrequency crawlFrequency;

  protected int maxDepth;
  protected boolean breadthFirst;

  //
  // protected Date nextRun;

  public Crawl(
      String name,
      Set<ResourceURI> seedResourceURIs,
      CrawlFrequency crawlFrequency,
      int maxDepth,
      boolean breadthFirst) {
    super(name);

    if (seedResourceURIs != null) this.seedResourceURIs.addAll(seedResourceURIs);

    this.crawlFrequency = crawlFrequency;
    this.maxDepth = maxDepth;
    this.breadthFirst = breadthFirst;
  }

  public Crawl() {

    this.seedResourceURIs = new HashSet<>();
    this.crawlSessions = new HashSet<>();
  }
}
