package com.walterjwhite.ip.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PublicIPAddressLookupProviderTest {
  @Test
  void testGetPublicIPAddress() throws Exception {
    final String apiipfiyIPAddress = PublicIPAddressLookupProvider.APIIPIFY.getPublicIPAddress();
    Assertions.assertThat(apiipfiyIPAddress).isNotNull();

    final String httpbinIPAddress = PublicIPAddressLookupProvider.HTTPBIN.getPublicIPAddress();
    Assertions.assertThat(httpbinIPAddress).isNotNull();
    Assertions.assertThat(httpbinIPAddress).isEqualTo(apiipfiyIPAddress);

    final String jsonIPAddress = PublicIPAddressLookupProvider.JSONIP.getPublicIPAddress();
    Assertions.assertThat(jsonIPAddress).isNotNull();
    Assertions.assertThat(jsonIPAddress).isEqualTo(apiipfiyIPAddress);
  }
}
