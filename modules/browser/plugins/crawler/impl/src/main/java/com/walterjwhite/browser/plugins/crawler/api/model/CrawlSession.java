package com.walterjwhite.browser.plugins.crawler.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
@NoArgsConstructor
public class CrawlSession extends AbstractEntity {

  protected LocalDateTime startTime;

  protected Crawl crawl;

  @EqualsAndHashCode.Exclude
  protected List<CrawlSessionResourceURI> crawlSessionResourceURIs = new ArrayList<>();

  public CrawlSession(Crawl crawl) {
    this();
    this.crawl = crawl;
  }
}
