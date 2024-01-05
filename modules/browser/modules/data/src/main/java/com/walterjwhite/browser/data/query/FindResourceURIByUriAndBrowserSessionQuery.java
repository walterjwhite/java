package com.walterjwhite.browser.data.query;

import com.walterjwhite.browser.data.BrowserSession;
import com.walterjwhite.browser.data.BrowserSessionResourceURI;
import com.walterjwhite.browser.data.ResourceURI;
import com.walterjwhite.datastore.query.AbstractSingularQuery;
import lombok.Getter;

@Getter
public class FindResourceURIByUriAndBrowserSessionQuery
    extends AbstractSingularQuery<BrowserSessionResourceURI> {
  protected final ResourceURI resourceURI;
  protected final BrowserSession browserSession;

  public FindResourceURIByUriAndBrowserSessionQuery(
      ResourceURI resourceURI, BrowserSession browserSession) {
    super(BrowserSessionResourceURI.class, false);
    this.resourceURI = resourceURI;
    this.browserSession = browserSession;
  }

  //    final CriteriaQueryConfiguration<BrowserSessionResourceURI>
  //        browserSessionResourceURICriteriaQueryConfiguration = getCriteriaQuery();
  //
  //    Predicate browserSessionCondition =
  //        criteriaBuilder.equal(
  //            browserSessionResourceURICriteriaQueryConfiguration
  //                .getRoot()
  //                .get(BrowserSessionResourceURI_.browserSession),
  //            browserSession);
  //    Predicate resourceURICondition =
  //        criteriaBuilder.equal(
  //            browserSessionResourceURICriteriaQueryConfiguration
  //                .getRoot()
  //                .get(BrowserSessionResourceURI_.resourceURI),
  //            resourceURI);
  //    browserSessionResourceURICriteriaQueryConfiguration
  //        .getCriteriaQuery()
  //        .where(browserSessionCondition, resourceURICondition);
  //    return entityManager
  //        .createQuery(browserSessionResourceURICriteriaQueryConfiguration.getCriteriaQuery())
  //        .getSingleResult();
}
