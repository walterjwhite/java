package com.walterjwhite.ip.impl.service;

public class TestFreeGeoIPPublicIPLookupService {
  public static void main(final String[] arguments) throws Exception {
    final FreeGeoIPPublicIPLookupService lookupService = new FreeGeoIPPublicIPLookupService();

    processIPAddress(lookupService.getPublicIPAddress());
  }

  private static void processIPAddress(final String ipAddress) {}
}
