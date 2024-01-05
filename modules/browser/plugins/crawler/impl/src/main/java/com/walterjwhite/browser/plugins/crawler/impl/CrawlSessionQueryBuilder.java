package com.walterjwhite.browser.plugins.crawler.impl;

import com.walterjwhite.browser.plugins.crawler.api.query.FindCrawlSessionResourceByCrawlSession;
import com.walterjwhite.datastore.api.annotation.Supports;

@Supports(FindCrawlSessionResourceByCrawlSession.class)
public class CrawlSessionQueryBuilder {} /*
    extends JpaCriteriaQueryBuilder<
        CrawlSessionResourceURI, / *List* / CrawlSessionResourceURI,
        FindCrawlSessionResourceByCrawlSession> {

  @Override
  protected Predicate buildPredicate(
      CriteriaBuilder criteriaBuilder,
      CriteriaQueryConfiguration criteriaQueryConfiguration,
      FindCrawlSessionResourceByCrawlSession queryConfiguration) {
    Predicate crawlSessionCondition =
        criteriaBuilder.equal(
            criteriaQueryConfiguration.getRoot().get(CrawlSessionResourceURI_.crawlSession),
            queryConfiguration.getCrawlSession());
    Predicate notCrawledCondition =
        criteriaBuilder.equal(
            criteriaQueryConfiguration.getRoot().get(CrawlSessionResourceURI_.crawlJobStatus),
            CrawlJobStatus.New);

    // return criteriaQueryConfiguration.getCriteriaQuery();
    // AND the predicates from above
    return null;
  }
}
*/
