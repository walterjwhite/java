package com.walterjwhite.browser.plugins.crawler.generator;

// import com.walterjwhite.browser.api.handler.PostGetHandler;
// import com.walterjwhite.browser.api.model.BrowserSessionResourceURI;
// import com.walterjwhite.browser.api.model.ResourceURI;
// import com.walterjwhite.browser.api.service.BrowserService;
// import com.walterjwhite.browser.data.query.FindResourceURIByUriQuery;
// import com.walterjwhite.browser.impl.service.urifilter.AllURIFilter;
// import com.walterjwhite.browser.plugins.crawler.api.model.CrawlSessionResourceURI;
// import com.walterjwhite.browser.plugins.crawler.api.service.CrawlerService;
// import com.walterjwhite.datastore.api.repository.Repository;
// import jakarta.inject.Inject;
// import jakarta.inject.Provider;
// import javax.transaction.Transactional;
// import org.openqa.selenium.WebElement;
// public abstract class AbstractResourceGenerator implements PostGetHandler {
//  protected final CrawlerService crawlerService;
//  //  protected final ResourceURIQueue resourceURIQueue;
//  protected final AllURIFilter allURIFilter;
//  protected final Provider<Repository> repositoryProvider;
//  @Inject
//  public AbstractResourceGenerator(
//      // ResourceURIQueue resourceURIQueue,
//      AllURIFilter allURIFilter,
//      //      BrowserService browserService,
//      CrawlerService crawlerService,
//      Provider<Repository> repositoryProvider) {
//    this.allURIFilter = allURIFilter;
//    this.crawlerService = crawlerService;
//    this.repositoryProvider = repositoryProvider;
//  }
//  protected boolean isPostProcess(final String uri) {
//    if (crawlerService.getDepth() < crawlerService.getCrawlSession().getCrawl().getMaxDepth()) {
//      return (isDoPostProcess(uri));
//    }
//    return false;
//  }
//  protected abstract boolean isDoPostProcess(final String uri);
//  @Transactional
//  protected void saveResourceURI(
//      BrowserSessionResourceURI fromBrowserSessionResourceURI, WebElement webElement) {
//    final String url = webElement.getAttribute("href");
//    if (isURLBlocked(url)) {
//      return;
//    }
//    repositoryProvider
//        .get()
//        .create(
//            new CrawlSessionResourceURI(
//                crawlerService.getCrawlSession(),
//                get(url),
//                crawlerService.getDepth() + 1,
//                fromBrowserSessionResourceURI));
//  }
//  protected boolean isURLBlocked(final String url) {
//    return allURIFilter.matches(url);
//  }
//  protected abstract Iterable<WebElement> doGet(BrowserService browserService);
//  // @Transactional(Transactional.TxType.REQUIRES_NEW)
//  // create the entity if the query returns no records (using the default constructor)
//  // @AutoCreate
//  // @Transactional
//  protected ResourceURI get(final String uri) {
//    return repositoryProvider
//        .get()
//        .query(new FindResourceURIByUriQuery(uri) /*, PersistenceOption.Create*/);
//  }
//  @Override
//  public void doPostGet(
//      BrowserService browserService, BrowserSessionResourceURI browserSessionResourceURI) {
//    for (final WebElement webElement : doGet(browserService)) {
//      saveResourceURI(browserSessionResourceURI, webElement);
//    }
//  }
// }
