package com.walterjwhite.browser.plugins.crawler.impl.postprocessor;

import com.walterjwhite.browser.api.handler.PagePostProcessor;
import com.walterjwhite.browser.api.model.BrowserSessionResourceURI;

public class TechbargainsPostProcessorService implements PagePostProcessor {
  @Override
  public void doPostProcess(
      BrowserService browserService, BrowserSessionResourceURI browserSessionResourceURI) {
    try {
      removeHeader(browserService);
      removeFooter(browserService);
    } catch (Exception e) {
      handlePostProcessError(e);
    }
  }

  protected void handlePostProcessError(Exception e) {}

  @Override
  public boolean isPostProcess(final String uri) {
    return (uri.startsWith("www.techbargains.com") || uri.startsWith("techbargains.com"));
  }

  protected void removeHeader(BrowserService browserService) {
    browserService.deleteElements("document.getElementsByClassName('navbar navbar-fixed-top');");
  }

  protected void removeFooter(BrowserService browserService) {
    browserService.deleteElements("document.getElementsByClassName('footer fluid-container');");
  }
}
