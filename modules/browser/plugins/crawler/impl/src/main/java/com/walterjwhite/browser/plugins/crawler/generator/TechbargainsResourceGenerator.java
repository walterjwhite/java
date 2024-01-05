package com.walterjwhite.browser.plugins.crawler.generator;

// import com.walterjwhite.browser.api.service.BrowserService;
// import com.walterjwhite.browser.impl.service.urifilter.AllURIFilter;
// import com.walterjwhite.browser.plugins.crawler.api.service.CrawlerService;
// import com.walterjwhite.datastore.api.repository.Repository;
// import jakarta.inject.Inject;
// import jakarta.inject.Provider;
// import org.openqa.selenium.By;
// import org.openqa.selenium.WebElement;
// public class TechbargainsResourceGenerator extends AbstractResourceGenerator {
//  @Inject
//  public TechbargainsResourceGenerator(
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
//    return uri.contains("techbargains.com");
//  }
//  protected Iterable<WebElement> doGet(BrowserService browserService) {
//    return (browserService
//        .getWebDriver()
//        .findElements(By.xpath("//*[@id=\"recent-deals\"]/div/div[2]/div[1]/h4/a")));
//  }
// }
