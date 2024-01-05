package com.walterjwhite.browser.plugins.crawler.impl.postprocessor;

import com.walterjwhite.browser.api.handler.PagePostProcessor;
import com.walterjwhite.browser.api.model.BrowserSessionResourceURI;

public class CraigslistPostProcessorService implements PagePostProcessor {
  public boolean isPostProcess(final String uri) {
    return (uri.contains("craigslist.org"));
  }

  protected void removeQueryBox(BrowserService browserService) {
    browserService.deleteElements("document.getElementsByClassName('querybox');");
  }

  protected void removeSearchOptions(BrowserService browserService) {
    browserService.deleteElements("document.getElementsByClassName('search-options')[0];");
  }

  protected void removeGlobalHeader(BrowserService browserService) {
    browserService.deleteElements("document.getElementsByClassName('global-header');");
  }

  protected void removeFooter(BrowserService browserService) {
    browserService.deleteElements("document.getElementsByClassName('clfooter');");
  }

  protected void removeBacktoTop(BrowserService browserService) {
    browserService.deleteElements("document.getElementsByClassName('backtotop button');");
    browserService.deleteElements("document.getElementsByClassName('backtotop button');");
  }

  @Override
  public void doPostProcess(
      BrowserService browserService, BrowserSessionResourceURI browserSessionResourceURI) {
    try {
      removeQueryBox(browserService);
      removeSearchOptions(browserService);
      removeGlobalHeader(browserService);
      removeFooter(browserService);
      removeBacktoTop(browserService);
    } catch (Exception e) {
      handlePostProcessError(e);
    }
  }

  protected void handlePostProcessError(Exception e) {}
}
