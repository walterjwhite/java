package com.walterjwhite.browser.plugins.crawler.generator;

// import com.walterjwhite.browser.api.service.BrowserService;
// import com.walterjwhite.browser.impl.service.urifilter.AllURIFilter;
// import com.walterjwhite.browser.plugins.crawler.api.service.CrawlerService;
// import com.walterjwhite.datastore.api.repository.Repository;
// import jakarta.inject.Inject;
// import jakarta.inject.Provider;
// import org.openqa.selenium.By;
// import org.openqa.selenium.WebElement;
/// **
// * Simply returns all links on the current page as resources to crawl if the max depth is greater
// * than the current depth.
// */
// public class DefaultResourceGenerator extends AbstractResourceGenerator {
//  @Inject
//  public DefaultResourceGenerator(
//      AllURIFilter allURIFilter,
//      //      BrowserService browserService,
//      CrawlerService crawlerService,
//      Provider<Repository> repositoryProvider) {
//    super(
//        allURIFilter,
//        //        browserService,
//        crawlerService,
//        repositoryProvider);
//  }
//  @Override
//  protected boolean isDoPostProcess(String uri) {
//    return true;
//  }
//  protected Iterable<WebElement> doGet(BrowserService browserService) {
//    return (browserService.getWebDriver().findElements(By.tagName("a")));
//  }
// }
