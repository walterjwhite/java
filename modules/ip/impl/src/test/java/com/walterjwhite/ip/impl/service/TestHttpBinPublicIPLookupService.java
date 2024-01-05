package com.walterjwhite.ip.impl.service;

public class TestHttpBinPublicIPLookupService {
  public static void main(final String[] arguments) throws Exception {
    final HttpBinPublicIPLookupService lookupService = new HttpBinPublicIPLookupService();

    logIP(lookupService.getPublicIPAddress());
  }

  private static void logIP(final String ipaddress) {}
}
