package com.walterjwhite.browser.plugins.crawler.impl.service;
// import com.walterjwhite.browser.api.model.BrowserSessionResourceURI;
// import com.walterjwhite.browser.data.query.FindResourceURIByUriAndBrowserSessionQuery;
// import com.walterjwhite.browser.impl.service.urifilter.AllURIFilter;
// import com.walterjwhite.browser.plugins.crawler.api.service.ResourceURIQueue;
// import com.walterjwhite.datastore.api.repository.Repository;
// import java.util.Set;
// import jakarta.inject.Inject;
// public class DefaultResourceURIQueue implements ResourceURIQueue {
//  protected final AllURIFilter allURIFilter;
//  protected final Repository repository;
//  @Inject
//  public DefaultResourceURIQueue(AllURIFilter allURIFilter, Repository repository) {
//    this.allURIFilter = allURIFilter;
//    this.repository = repository;
//  }
//  //  @Transactional(Transactional.TxType.REQUIRES_NEW)
//  @Override
//  public boolean add(BrowserSessionResourceURI sessionResourceURI) {
//    if (isBlocked(sessionResourceURI)) return false;
//    if (wasPreviouslyCrawled(sessionResourceURI)) return false;
//    doAdd(sessionResourceURI);
//    return true;
//  }
//  protected boolean isBlocked(BrowserSessionResourceURI sessionResourceURI) {
//    return allURIFilter.matches(sessionResourceURI.getResourceURI().getName());
//  }
//  protected boolean wasPreviouslyCrawled(BrowserSessionResourceURI sessionResourceURI) {
//    try {
//      repository.query(
//          new FindResourceURIByUriAndBrowserSessionQuery(
//              sessionResourceURI.getResourceURI(), sessionResourceURI.getBrowserSession()));
//      return true;
//    } catch (/*NoResultException*/ RuntimeException e) {
//      return false;
//    }
//  }
//  protected void doAdd(BrowserSessionResourceURI sessionResourceURI) {
//    repository.create(sessionResourceURI);
//  }
//  @Override
//  public boolean addAll(Set<BrowserSessionResourceURI> sessionResourceURIs) {
//    boolean added = false;
//    for (BrowserSessionResourceURI sessionResourceURI : sessionResourceURIs) {
//      if (add(sessionResourceURI)) {
//        added = true;
//      }
//    }
//    return (added);
//  }
// }
