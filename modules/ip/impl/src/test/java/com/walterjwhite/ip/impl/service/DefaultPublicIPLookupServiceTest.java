package com.walterjwhite.ip.impl.service;

import com.walterjwhite.google.guice.GuiceHelper;
import com.walterjwhite.ip.api.service.PublicIPLookupService;
import com.walterjwhite.ip.impl.PublicIPLookupModule;
import org.junit.Before;
import org.junit.Test;

public class DefaultPublicIPLookupServiceTest {
  protected PublicIPLookupService publicIPLookupService;

  @Before
  public void before() {
    GuiceHelper.addModules(new PublicIPLookupModule());
    GuiceHelper.setup();

    publicIPLookupService =
        GuiceHelper.getGuiceApplicationInjector().getInstance(PublicIPLookupService.class);
  }

  @Test
  public void testLookup() throws Exception {
    processPublicIPAddress(publicIPLookupService.getPublicIPAddress());
  }

  protected void processPublicIPAddress(final String ipAddress) {}
}
