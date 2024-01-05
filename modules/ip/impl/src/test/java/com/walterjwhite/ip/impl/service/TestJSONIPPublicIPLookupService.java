package com.walterjwhite.ip.impl.service;

public class TestJSONIPPublicIPLookupService {
  public static void main(final String[] arguments) throws Exception {
    final JSONIPPublicIPLookupService lookupService = new JSONIPPublicIPLookupService();

    logIP(lookupService.getPublicIPAddress());
  }

  private static void logIP(final String ip) {}
}
