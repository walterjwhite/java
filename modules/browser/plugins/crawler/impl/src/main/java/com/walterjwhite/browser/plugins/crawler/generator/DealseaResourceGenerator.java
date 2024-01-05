package com.walterjwhite.browser.plugins.crawler.generator;

// import com.walterjwhite.browser.api.service.BrowserService;
// import com.walterjwhite.browser.impl.service.urifilter.AllURIFilter;
// import com.walterjwhite.browser.plugins.crawler.api.service.CrawlerService;
// import com.walterjwhite.datastore.api.repository.Repository;
// import jakarta.inject.Inject;
// import jakarta.inject.Provider;
// import org.openqa.selenium.By;
// import org.openqa.selenium.WebElement;
// public class DealseaResourceGenerator extends AbstractResourceGenerator {
//  @Inject
//  public DealseaResourceGenerator(
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
//    return uri.contains("dealsea.com");
//  }
//  protected Iterable<WebElement> doGet(BrowserService browserService) {
//    return (browserService
//        .getWebDriver()
//        .findElements(By.xpath("//*[@id=\"fp-deals\"]/div/div[2]/strong/a")));
//  }
// }
