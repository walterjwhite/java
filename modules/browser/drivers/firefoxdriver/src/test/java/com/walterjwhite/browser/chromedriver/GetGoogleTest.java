package com.walterjwhite.browser.chromedriver;

import com.walterjwhite.browser.api.model.ResourceURI;
import com.walterjwhite.browser.api.service.BrowserService;
import com.walterjwhite.google.guice.GuiceHelper;
import org.junit.Before;
import org.junit.Test;

public class GetGoogleTest {
  @Before
  public void onBefore() {
    GuiceHelper.addModules(new JBrowserDriverTestModule(getClass()));
    GuiceHelper.setup();
  }

  @Test
  public void testGet() {
    BrowserService browserServer =
        GuiceHelper.getGuiceApplicationInjector().getInstance(BrowserService.class);
    browserServer.get(new ResourceURI("https://www.google.com"));
  }
}
