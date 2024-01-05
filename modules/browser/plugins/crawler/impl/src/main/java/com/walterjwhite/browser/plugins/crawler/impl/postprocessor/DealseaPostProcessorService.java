package com.walterjwhite.browser.plugins.crawler.impl.postprocessor;

import com.walterjwhite.browser.api.handler.PagePostProcessor;
import com.walterjwhite.browser.api.model.BrowserSessionResourceURI;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DealseaPostProcessorService implements PagePostProcessor {
  @Override
  public void doPostProcess(
      BrowserService browserService, BrowserSessionResourceURI browserSessionResourceURI) {
    try {
      removeHeader(browserService);
      removeCategories(browserService);
      removeTitle(browserService);
      removeBottomSection(browserService);
      removeFooter(browserService);
    } catch (Exception e) {
      LOGGER.warn("exception", e);
    }
  }

  @Override
  public boolean isPostProcess(final String uri) {
    return (uri.startsWith("www.dealsea.com") || uri.startsWith("dealsea.com"));
  }

  protected void removeCategories(BrowserService browserService) {
    browserService.deleteElements("document.getElementById('categories');");
  }

  protected void removeHeader(BrowserService browserService) {
    browserService.deleteElements("document.getElementById('header');");
  }

  protected void removeTitle(BrowserService browserService) {
    browserService.deleteElements("document.getElementById('main-title');");
  }

  protected void removeBottomSection(BrowserService browserService) {
    browserService.deleteElements("document.getElementsByClassName('r-container');");
    browserService.deleteElements("document.getElementsByClassName('r-container');");
  }

  protected void removeFooter(BrowserService browserService) {
    browserService.deleteElements("document.getElementsByTagName('footer');");
  }
}
